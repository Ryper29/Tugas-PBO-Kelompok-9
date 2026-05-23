import java.util.Random;

class Player {
    private String name;
    private String race;
    private double health;
    private Weapon weapon;
    private Armor armor;

    public static int totalCharacters = 0;

    public Player(String name, String race, double health) {
        this.name = name;
        this.race = race;
        this.health = health;
        totalCharacters++;
    }

    public String getName() { return this.name; }
    public String getRace() { return this.race; }
    public double getHealth() { return this.health; }
    public void setHealth(double health) { this.health = health; }
    public Weapon getWeapon() { return this.weapon; }
    public Armor getArmor() { return this.armor; }

    public void equipWeapon(Weapon weapon) { this.weapon = weapon; }
    public void equipArmor(Armor armor) { this.armor = armor; }

    protected int rollD20() {
        Random rand = new Random();
        return rand.nextInt(20) + 1;
    }

    public void attack(Player opponent) {
        System.out.println(this.name + " melakukan serangan dasar!");
    }

    public void defence(double damageIn) {
        double actualDamage = damageIn - (this.armor != null ? this.armor.getDefencePower() : 0);
        if (actualDamage < 0) actualDamage = 0;

        this.health -= actualDamage;
        if (this.health < 0) this.health = 0;

        System.out.println(this.name + " menerima " + String.format("%.1f", actualDamage) + " damage. (Sisa HP: " + String.format("%.1f", this.health) + ")");

        if(this.health == 0) {
            System.out.println(this.name + " telah gugur!");
        }
    }

    public void displayProfile() {
        System.out.println("\n--- [ KARAKTER STATS ] ---");
        System.out.println("Nama   : " + this.name + " (" + this.race + ")");
        System.out.println("Health : " + this.health + " HP");
        if (this.weapon != null) this.weapon.display();
        if (this.armor != null) this.armor.display();
        System.out.println("--------------------------");
    }
}

class Fighter extends Player {
    private String combatStyle;

    public Fighter(String name, String race, double health, String combatStyle) {
        super(name, race, health);
        this.combatStyle = combatStyle;
    }

    @Override
    public void attack(Player opponent) {
        if (this.getHealth() <= 0) return;

        System.out.println("\nGiliran: " + this.getName() + " (Fighter - " + this.combatStyle + ")");
        int diceRoll = rollD20();
        System.out.println("Hasil lempar dadu D20: " + diceRoll);

        if (diceRoll == 1) {
            System.out.println("CRITICAL FAIL! Serangan " + this.getName() + " meleset total!");
        } else {
            double baseDamage = this.getWeapon() != null ? this.getWeapon().getAttackPower() : 1;
            double totalDamage = baseDamage + (diceRoll * 0.2);

            System.out.println(this.getName() + " menyerang " + opponent.getName() + " dengan " + this.getWeapon().getName() + "!");
            opponent.defence(totalDamage);
        }
    }
}

class Wizard extends Player {
    private int mana;

    public Wizard(String name, String race, double health, int mana) {
        super(name, race, health);
        this.mana = mana;
    }

    @Override
    public void attack(Player opponent) {
        if (this.getHealth() <= 0) return;

        System.out.println("\nGiliran: " + this.getName() + " (Wizard)");
        int diceRoll = rollD20();
        System.out.println("Hasil lempar dadu D20: " + diceRoll);

        if (this.mana >= 5 && diceRoll > 5) {
            System.out.println(this.getName() + " merapalkan spell 'Magic Missile' ke arah " + opponent.getName() + "!");
            this.mana -= 5;
            double spellDamage = (this.getWeapon() != null ? this.getWeapon().getAttackPower() : 0) + 4;
            opponent.defence(spellDamage);
        } else if (this.mana < 5) {
            System.out.println("Mana tidak cukup! " + this.getName() + " hanya memukul dengan tongkat!");
            opponent.defence(2);
        } else {
             System.out.println("Spell gagal dirapalkan karena hasil dadu terlalu rendah!");
        }
    }
}

class Weapon {
    private String name;
    private double attackPower;

    public Weapon(String name, double attackPower) {
        this.name = name;
        this.attackPower = attackPower;
    }
    public String getName() { return this.name; }
    public double getAttackPower() { return this.attackPower; }
    public void display() { System.out.println("Weapon : " + this.name + " (ATK: " + this.attackPower + ")"); }
}

class Armor {
    private String name;
    private double defencePower;

    public Armor(String name, double defencePower) {
        this.name = name;
        this.defencePower = defencePower;
    }
    public double getDefencePower() { return this.defencePower; }
    public void display() { System.out.println("Armor  : " + this.name + " (DEF: " + this.defencePower + ")"); }
}

public class DND {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("DUNGEONS & DRAGONS: TUGAS PBO EDITION");
        System.out.println("=========================================");

        Fighter aurelius = new Fighter("Aurelius", "Semi Dragon", 9.0, "Brawler");
        Fighter andrea = new Fighter("Andrea", "Human", 11.0, "Swordsman");
        Wizard kaelen = new Wizard("Kaelen", "Elf", 10.0, 30);

        Weapon cakar = new Weapon("Dragon Claw", 2);
        Weapon pedang = new Weapon("Iron Sword", 3);
        Weapon tongkat = new Weapon("Wooden Staff", 1);

        Armor sisik = new Armor("Dragon Scale", 2);
        Armor bajuZirah = new Armor("Light Armor", 2);
        Armor jubah = new Armor("Cloth Robe", 1);

        aurelius.equipWeapon(cakar);
        aurelius.equipArmor(sisik);

        andrea.equipWeapon(pedang);
        andrea.equipArmor(bajuZirah);

        kaelen.equipWeapon(tongkat);
        kaelen.equipArmor(jubah);

        Fighter bossMonster = new Fighter("Goblin King", "Goblin", 35.0, "Giant Club Smasher");
        Weapon gadaBesar = new Weapon("Giant Spiked Club", 4);
        Armor kulitKeras = new Armor("Hardened Goblin Skin", 2);

        bossMonster.equipWeapon(gadaBesar);
        bossMonster.equipArmor(kulitKeras);

        System.out.println("\n>>> MENGHADAPI BOS MONSTER <<<");
        bossMonster.displayProfile();

        System.out.println("\nTotal Karakter dalam Campaign ini (termasuk Bos): " + Player.totalCharacters);

        System.out.println("\n>>> COMBAT INITIATED! <<<");

        System.out.println("\n[ ROUND 1: PARTY ATTACKS! ]");
        aurelius.attack(bossMonster);
        andrea.attack(bossMonster);
        kaelen.attack(bossMonster);

        System.out.println("\n[ ROUND 2: BOSS COUNTERATTACK! ]");
        System.out.println("Goblin King mengamuk dan mengincar Andrea yang berada di barisan depan!");
        bossMonster.attack(andrea);

        System.out.println("\n>>> END OF BATTLE SIMULATION <<<");
    }
}