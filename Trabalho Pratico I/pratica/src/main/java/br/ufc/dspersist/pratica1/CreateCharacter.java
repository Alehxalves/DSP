package br.ufc.dspersist.pratica1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/*
 * Crie uma classe Java para cadastrar dados relacionados à entidade definida na questão 1. 
 * A classe deve receber dados via teclado e os salvar em um arquivo JSON
 */

public class CreateCharacter {
    private List<Character> characters;

    public CreateCharacter() {
        characters = new ArrayList<Character>();
    }

    public Character CreateChar(String nickname, int intClass) throws Exception {
        Character character = new Character();
        // Gerar uma identificação aleatória.
        Random random = new Random();
        int id = random.nextInt(999 - 1) + 1;

        character.setId(id);
        character.setNickname(nickname);
        Enum<Class> classe;

        switch (intClass) {
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
                throw new Exception("Invalid Class.");
        }

        character.setClasse(classe);
        character = getSpellAndWeapon(character);
        characters.add(character);

        System.out.println("character created successfully!");

        return character;
    }

    private Character getSpellAndWeapon(Character character) {
        switch (character.getClasse()) {
            case "KNIGHT":
                character.setSpell("RAGE");
                character.setWeapon("SWORD");
                break;
            case "MAGE":
                character.setSpell("FIRE SPELL");
                character.setWeapon("WAND");
                break;
            case "PALADIN":
                character.setSpell("DIVINE POWER");
                character.setWeapon("BOW");
                break;
            case "DRUID":
                character.setSpell("HEAL");
                character.setWeapon("STAFF");
                break;
            default:
                break;
        }
        return character;
    }

    public void saveCharactersInJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        if (getCharacters().size() == 0)
            return;
        if (getCharacters().size() == 1) {
            objectMapper.writeValue(new File(("character.json")), characters.get(0));
        } else {
            objectMapper.writeValue(new File(("characters.json")), characters);
        }
        System.out.println("Sucess!");
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public static void main(String[] args) {
        CreateCharacter createC = new CreateCharacter();
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        int intOption;
        String line;

        while (true) {
            try {
                System.out.println("==============================");
                System.out.println("(1) Create Character\n"
                        + "(2) Save characters in JSON\n"
                        + "(3) Exit");
                System.out.println("==============================");
                intOption = scannerInt.nextInt();

                CLS.clearConsole();
                if (intOption == 1) {
                    System.out.print("Character name: ");
                    line = scannerString.nextLine();
                    System.out.println("CLASSE: (1) Knight | (2) Mage | (3) Paladin | (4) Druid");
                    System.out.print("Choose your class: ");
                    intOption = scannerInt.nextInt();

                    createC.CreateChar(line, intOption);
                } else if (intOption == 2) {
                    createC.saveCharactersInJson();
                } else if (intOption == 3) {
                    break;
                } else {
                    System.out.println("Invalid option");
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        scannerInt.close();
        scannerString.close();
    }
}
