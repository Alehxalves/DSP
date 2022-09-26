package br.ufc.dspersist.pratica1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

/**
 * JsonFileToXmlAndCsv
 */
public class JsonFileToXmlAndCsv {

    public static void convertJsonToXml(String filePath) throws Exception {
        validateJsonFile(filePath);
        File fileJSON = new File(filePath);
        // Converter arquivo json para uma string.
        String jsonString = readFileAsString(filePath);

        // root do arquivo xml
        String[] root = fileJSON.getName().split(".json");

        ObjectMapper objectMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();

        JsonNode jsonTree = objectMapper.readTree(jsonString);

        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

        // Desserializando json e serializar xml.
        if (jsonTree.isArray()) {
            JsonNode[] jsonArray = objectMapper.readValue(jsonString, JsonNode[].class);
            xmlMapper.writer().withRootName(root[0])
                    .writeValue(new File(root[0].concat(".xml")), jsonArray);
        } else {
            xmlMapper.writer().withRootName(root[0])
                    .writeValue(new File(root[0].concat(".xml")), jsonTree);
        }

        System.out.println("Sucess!");
    }

    public static String readFileAsString(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void convertJsonToCsv(String filePath) throws Exception {
        validateJsonFile(filePath);
        File fileJSON = new File(filePath);

        String jsonString = readFileAsString(filePath);
        JsonNode jsonTree = new ObjectMapper().readTree(jsonString);

        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();

        if (jsonTree.isArray()) {
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
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(fileCsvName[0].concat(".csv")), jsonTree);

    }

    public static void validateJsonFile(String filePath) {
        try {
            File fileJSON = new File(filePath);

            if (!fileJSON.exists()) {
                throw new Exception("Arquivo não encontrado.");
            } else if (!fileJSON.getPath().contains("json")) {
                throw new Exception("Arquivo não é um JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        if (!ValidateArgs.validate(args))
            return;

        String filePath = args[0];

        convertJsonToXml(filePath);
        convertJsonToCsv(filePath);
    }
}