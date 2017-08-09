/**
 * Copyright 2017 Hortonworks.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *   http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.accumulo.core.client.Scanner;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.hadoop.io.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class AccumuloMain {

    public static void main(String[] args) {

        get("/vehicleData", "application/json", (req, res) -> {

            String start = req.queryParams("start");
            String stop = req.queryParams("stop");

            if (start == null || start.isEmpty() || stop == null || stop.isEmpty()) {
                start = "1501097707005";  //If not time is passed, set a sample range
                stop = "1501105148000";
            }

            Scanner scan = new AccumuloConnectionFactory().getScanner();

            scan.setRange(new Range(start, stop));
            scan.fetchColumnFamily(new Text("rvi"));

            ArrayList<GpsPojo> list = new ArrayList<GpsPojo>();
            GpsPojo gps = null;
            String currentRowId = null;

            for (Map.Entry<Key, Value> entry : scan) {
                Text row = entry.getKey().getRow();
                Value value = entry.getValue();
                String cq = entry.getKey().getColumnQualifier().toString();
                if (currentRowId == null || !currentRowId.equals(row.toString())) {
                    if (currentRowId != null) {
                        list.add(gps);
                    }
                    gps = new GpsPojo();
                    gps = helperSwitch(gps, cq, value);
                    currentRowId = row.toString();
                } else {
                    gps = helperSwitch(gps, cq, value);
                }
            }
            list.add(gps);
            Gson gson = new Gson();
            Type listOfTestObject = new TypeToken<List<GpsPojo>>() {
            }.getType();
            String s = gson.toJson(list, listOfTestObject);
            List<GpsPojo> list2 = gson.fromJson(s, listOfTestObject);
            return gson.toJson(list2);

        });
    }

    public static GpsPojo helperSwitch(GpsPojo gps, String cf, Value value) {
        if (cf.equals("longitude")) {
            gps.setLongitude(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("latitude")) {
            gps.setLatitude(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("speed")) {
            gps.setSpeed(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("vehicleTime")) {
            gps.setTime(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("vehicleId")) {
            gps.setVehicleId(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("heading")) {
            gps.setHeading(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("altitude")) {
            gps.setAltitude(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("accuracy")) {
            gps.setAccuracy(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("speedlimit")) {
            gps.setSpeedlimit(value.toString().replaceAll("^\"|\"$", ""));
        } else if (cf.equals("speeding")) {
            gps.setSpeeding(value.toString().replaceAll("^\"|\"$", ""));
        }
        return gps;
    }
}