package br.ufc.dspersist.pratica1;

public class Character {
    private int id;
    private String nickname;
    private Enum<Class> classe;
    private String spell;
    private String weapon;

    public Character() {

    }

    public Character(int id, String nickname, Enum<Class> classe) {
        this.id = id;
        this.nickname = nickname;
        this.classe = classe;
        getSpellAndWeapon(classe);
    }

    private void getSpellAndWeapon(Enum<Class> classe) {
        switch (classe.name()) {
            case "KNIGHT":
                this.spell = "RAGE";
                this.weapon = "SWORD";
                break;
            case "MAGE":
                this.spell = "FIRE SPELL";
                this.weapon = "WAND";
                break;
            case "PALADIN":
                this.spell = "DIVINE POWER";
                this.weapon = "BOW";
                break;
            case "DRUID":
                this.spell = "HEAL";
                this.weapon = "STAFF";
                break;
            default:
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getClasse() {
        return classe.name();
    }

    public void setClasse(Enum<Class> classe) {
        this.classe = classe;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String toString() {
        return "{ id: " + getId() + ", nickname: " + getNickname() + ", classe: "
                + getClasse() + ", spell: " + getSpell() + ", weapon: " + getWeapon() + " }";
    }
}
