package br.ufc.dspersist.pratica1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class Controller {
    private List<Character> characters;
    private ObjectMapper objectMapper;

    public Controller() {
        characters = new ArrayList<Character>();
        objectMapper = new ObjectMapper();
    }

    public Character createCharacter(String nickname, int intClasse) throws Exception {
        // Gerar uma identificação aleatória.
        Random random = new Random();
        int id = random.nextInt(999 - 1) + 1;

        Enum<Class> classe;

        switch (intClasse) {
            case 1:
                classe = Class.KNIGHT;
                break;
            case 2:
                classe = Class.MAGE;
                break;
            case 3:
                classe = Class.PALADIN;
                break;
            case 4:
                classe = Class.DRUID;
                break;
            default:
                throw new Exception("Classe invalida.");
        }

        Character character = new Character(id, nickname, classe);
        characters.add(character);

        System.out.println("character created successfully!");
        System.out.println(character);

        saveCharactersJSON(characters);

        return character;
    }

    public List<Character> getCharacters() {
        return this.characters;
    }

    public void saveCharactersJSON(List<Character> characters) throws Exception {
        if (characters.size() == 1) {
            objectMapper.writeValue(new File(("character.json")), characters.get(0));
        } else {
            objectMapper.writeValue(new File(("characters.json")), characters);
        }

    }

    public void convertJsonToXml(String path) throws Exception {
        File fileJSON = new File(path);

        if (!fileJSON.exists()) {
            throw new Exception("Arquivo não encontrado.");
        } else if (!fileJSON.getPath().contains("json")) {
            throw new Exception("Arquivo não é um JSON.");
        }

        // Converter arquivo json para uma string.
        String jsonString = readFileAsString(path);

        Boolean isArray = false;

        // root do arquivo xml
        String[] root = fileJSON.getName().split(".json");

        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

        try {
            // Desserializando único objeto json para uma classe genérica da lib Jackson.
            JsonNode jsonObjct = objectMapper.readValue(jsonString, JsonNode.class);
            xmlMapper.writer().withRootName(root[0])
                    .writeValue(new File(root[0].concat(".xml")), jsonObjct);
        } catch (Exception e) {
            // Desserializando um array de objetos json para array de classe genérica da lib
            // Jackson
            JsonNode[] jsonObjcts = objectMapper.readValue(jsonString, JsonNode[].class);
            xmlMapper.writer().withRootName(root[0])
                    .writeValue(new File(root[0].concat(".xml")), jsonObjcts);
            isArray = true;
        }

        convertJsonToCsv(fileJSON, isArray);

        System.out.println("Sucess!");
    }

    public static String readFileAsString(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public void convertJsonToCsv(File fileJSON, Boolean isArray) throws Exception {
        JsonNode jsonTree = new ObjectMapper().readTree(fileJSON);

        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();

        if (isArray) {
            // Pegar o primeiro elemento do meu jsonTree
            JsonNode firstObject = jsonTree.elements().next();

            // ForEach no objeto para pegar cada campo do json e armazenar no
            // csvSchemaBuilder como uma coluna
            firstObject.fieldNames().forEachRemaining(fieldName -> {
                csvSchemaBuilder.addColumn(fieldName);

            });
        } else {
            jsonTree.fieldNames().forEachRemaining(fieldName -> {
                csvSchemaBuilder.addColumn(fieldName);

            });
        }

        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        // Nome do arquivo CSV
        String[] fileCsvName = fileJSON.getName().split(".json");

        CsvMapper csvMapper = new CsvMapper();
        // WriteFor rootType = tipo de class java a ser serializado.
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(fileCsvName[0].concat(".csv")), jsonTree);

    }

    public void compresFile(String filePath) throws Exception {
        CompressFile.compress(filePath);
    }

    public void showSHA1Hash(String filePath) throws Exception {
        String result = SHA1Hash.getSha1Hash(filePath);
        System.out.println("HASH SHA1: " + result.toString());
    }
}
