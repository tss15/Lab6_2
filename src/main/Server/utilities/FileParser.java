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
            JSONArray personList = (JSONArray) obj;
            collectionInput = saveIntoCollection(personList);

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
        Long newID = (Long)jsonObject.get("id");
        if(CollectionManager.IDChecker.contains(newID)){
            System.out.println("ID is duplicate, please insert valid input!");
        }
        else {
            CollectionManager.IDChecker.add(newID);
            lw.setId(newID);
        }
        //set Name
        lw.setName((String)jsonObject.get("name"));

        // set Coordinates
        JSONObject coordinatesObj = (JSONObject) jsonObject.get("coordinates");
        lw.setCoordinates(new Coordinates(Math.toIntExact((Long) coordinatesObj.get("x")), (Double)coordinatesObj.get("y")));

        /*
            parse String to LocalDateTime
        */

        // date in String
        String dateString = (String)jsonObject.get("creationDate");

        //build formatter
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        //Parse String to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString,formatter);
        lw.setCreationDate(dateTime);
        //System.out.println(p.getCreationDate());

        // set minimalPoint
        lw.setMinimalPoint((Long) jsonObject.get("minimalPoint"));

        // set difficulty
        String difficultyString = (String)jsonObject.get("difficulty");
        Difficulty difficultyEnum = Difficulty.valueOf(difficultyString);
        lw.setDifficulty(difficultyEnum);


        // set weight
        lw.setWeight(Math.toIntExact((Long)jsonObject.get("weight")));

        // set nationality
        String countryString = (String)jsonObject.get("nationality");
        Country countryEnum = Country.valueOf(countryString);
        lw.setNationality(countryEnum);
        //set author
        JSONObject authorObj = (JSONObject)jsonObject.get("author");
        lw.setAuthor(new Person(
                (String)authorObj.get("name"), (double)authorObj.get("weight"),(Color)authorObj.get("eye color"),(HairColor) authorObj.get("hair color"),(Country) authorObj.get("nationality"),(Location) authorObj.get("location")
        ));

        // set location
        JSONObject locationObj = (JSONObject)jsonObject.get("location");
        lw.setLocation(new Location(
                Math.toIntExact((Long)locationObj.get("x")),
                (long)locationObj.get("y"),
                (String)locationObj.get("name")
        ));

        return lw;
    }
}