package utilities;

import data.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

public class FileParser {

    public HashMap<Integer, LabWork> parse(String InputFileName){
        HashMap<Integer, LabWork> collectionInput = new HashMap<>();
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(InputFileName)) {
            Object obj = jsonParser.parse(reader);
            JSONArray labWorkList = (JSONArray) obj;
            collectionInput = saveIntoCollection(labWorkList);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return collectionInput;
    }


    /**
     * This method is used to convert JSONArray to HashMap then save into Collection
     * @param jsArr json array
     * @return HashMap is converted JSONArray
     */
    @SuppressWarnings("unchecked")
    private HashMap<Integer,LabWork> saveIntoCollection(JSONArray jsArr){
        HashMap<Integer,LabWork> HM = new HashMap<>();
        jsArr.forEach(p -> {
            try {
                Random r = new Random();
                Integer randomVal = Math.abs(r.nextInt());
                HM.put(randomVal, convertJsonObjIntoLabWork((JSONObject) p));
                //System.out.println(((JSONObject) p).toJSONString());
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        });
        return HM;
    }

    /**
     * This method convert JsonObj to LabWork
     * @param jsonObject json object
     * @return LabWork which is converted from JsonObject
     * @throws java.text.ParseException throw if occurs error
     */
    private LabWork convertJsonObjIntoLabWork(JSONObject jsonObject) throws java.text.ParseException {
        LabWork lw = new LabWork();
        // set ID
        Long newID = Long.parseLong(String.valueOf(jsonObject.get("id")));
        System.out.println(newID);
        if(CollectionManager.IDChecker.contains(newID)){
            System.out.println("ID is duplicate, please insert valid input!");
        }
        else {
            CollectionManager.IDChecker.add(newID);
            lw.setId(newID);
        }
        //set Name
        lw.setName((String)jsonObject.get("name"));
        System.out.println(lw.getName());

        // set Coordinates
        JSONObject coordinatesObj = (JSONObject) jsonObject.get("coordinates");
        lw.setCoordinates(new Coordinates(Math.toIntExact(Long.parseLong(String.valueOf(coordinatesObj.get("x")))), Double.parseDouble(String.valueOf(coordinatesObj.get("y")))));

        /*
            parse String to LocalDateTime
        */

//        // date in String
//        String dateString = (String)jsonObject.get("creationDate");
//
//        //build formatter
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
//        //Parse String to LocalDateTime
//        LocalDateTime dateTime = LocalDateTime.parse(dateString,formatter);
//        lw.setCreationDate(dateTime);
//        //System.out.println(p.getCreationDate());

        // set minimalPoint
        lw.setMinimalPoint(Long.parseLong(String.valueOf(jsonObject.get("minpoint"))));

        // set difficulty
        String difficultyString = (String)jsonObject.get("difficulty");
        Difficulty difficultyEnum = Difficulty.valueOf(difficultyString);
        lw.setDifficulty(difficultyEnum);


//         set weight
//        lw.setWeight(Math.toIntExact(Long.parseLong(String.valueOf(jsonObject.get("weight")))));

//        // set nationality
//        String countryString = (String)jsonObject.get("nationality");
//        Country countryEnum = Country.valueOf(countryString);
//        lw.setNationality(countryEnum);
        //set author
//         set location
        JSONObject authorObj = (JSONObject)jsonObject.get("author");
                JSONObject locationObj = (JSONObject)authorObj.get("location");
        //System.out.println(locationObj);
//                lw.setLocation(new Location(
//                        Math.toIntExact(Long.parseLong(String.valueOf(locationObj.get("x")))),
//                        Long.parseLong(String.valueOf(locationObj.get("y"))),
//                        (String)locationObj.get("name")
//                ));

        lw.setAuthor(new Person(
                (String)authorObj.get("name"), Double.parseDouble(String.valueOf(authorObj.get("weight"))),Color.valueOf(String.valueOf(authorObj.get("eye color"))),HairColor.valueOf(String.valueOf(authorObj.get("hair color"))),Country.valueOf(String.valueOf(authorObj.get("nationality"))), new Location(
                Integer.parseInt((String.valueOf(locationObj.get("x")))),
                Long.parseLong(String.valueOf(locationObj.get("y"))),
                (String)locationObj.get("name")
        )
        ));



        return lw;
    }
}