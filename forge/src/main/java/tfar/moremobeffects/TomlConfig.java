package tfar.moremobeffects;

import net.minecraftforge.common.ForgeConfigSpec;
import tfar.moremobeffects.platform.MultiloaderConfig;

public class TomlConfig implements MultiloaderConfig {

    @Override
    public double vulnerable() {
        return Server.vulnerable.get();
    }

    @Override
    public double exposed() {
        return Server.exposed.get();
    }

    @Override
    public double archery() {
        return Server.archery.get();
    }

    @Override
    public double aiming() {
        return Server.aiming.get();
    }

    @Override
    public double marksman() {
        return Server.marksman.get();
    }

    @Override
    public double inspired() {
        return Server.inspired.get();
    }

    @Override
    public double precise() {
        return Server.precise.get();
    }

    @Override
    public double brutality() {
        return Server.brutality.get();
    }

    @Override
    public double savage() {
        return Server.savage.get();
    }

    @Override
    public double archers_frenzy() {
        return Server.archers_frenzy.get();
    }

    @Override
    public double hasty() {
        return Server.hasty.get();
    }

    @Override
    public double empowered() {
        return Server.empowered.get();
    }

    @Override
    public double magic_up() {
        return Server.magic_up.get();
    }

    @Override
    public double arcane_boost() {
        return Server.arcane_boost.get();
    }

    @Override
    public double spell_haste() {
        return Server.spell_haste.get();
    }

    @Override
    public double focused() {
        return Server.focused.get();
    }

    @Override
    public double arcanic_conversion() {
        return Server.arcanic_conversion.get();
    }

    @Override
    public double arcanic_overload() {
        return Server.arcanic_overload.get();
    }

    @Override
    public double retribution() {
        return Server.retribution.get();
    }

    @Override
    public double domineering() {
        return Server.domineering.get();
    }

    @Override
    public double marked() {
        return Server.marked.get();
    }

    @Override
    public double mana_flare() {
        return Server.mana_flare.get();
    }

    @Override
    public double endergized() {
        return Server.endergized.get();
    }

    @Override
    public double enderphins() {
        return Server.enderphins.get();
    }

    @Override
    public double life_steal() {
        return Server.life_steal.get();
    }

    @Override
    public double armor_pierce() {
        return Server.armor_pierce.get();
    }

    @Override
    public double overheal() {
        return Server.overheal.get();
    }

    @Override
    public double summon_boost() {
        return Server.summon_boost.get();
    }

    @Override
    public double spell_exhaustion() {
        return Server.spell_exhaustion.get();
    }

    @Override
    public double disarming() {
        return Server.spell_exhaustion.get();
    }

    public static class Server {
        public static ForgeConfigSpec.DoubleValue vulnerable;
        public static ForgeConfigSpec.DoubleValue exposed;
        public static ForgeConfigSpec.DoubleValue archery;
        public static ForgeConfigSpec.DoubleValue aiming;
        public static ForgeConfigSpec.DoubleValue marksman;

        public static ForgeConfigSpec.DoubleValue inspired;
        public static ForgeConfigSpec.DoubleValue precise;

        public static ForgeConfigSpec.DoubleValue brutality;
        public static ForgeConfigSpec.DoubleValue savage;

        public static ForgeConfigSpec.DoubleValue archers_frenzy;
        public static ForgeConfigSpec.DoubleValue hasty;

        public static ForgeConfigSpec.DoubleValue empowered;
        public static ForgeConfigSpec.DoubleValue magic_up;
        public static ForgeConfigSpec.DoubleValue arcane_boost;

        public static ForgeConfigSpec.DoubleValue spell_haste;

        public static ForgeConfigSpec.DoubleValue focused;
        public static ForgeConfigSpec.DoubleValue arcanic_conversion;
        public static ForgeConfigSpec.DoubleValue arcanic_overload;
        public static ForgeConfigSpec.DoubleValue retribution;
        public static ForgeConfigSpec.DoubleValue domineering;
        public static ForgeConfigSpec.DoubleValue marked;
        public static ForgeConfigSpec.DoubleValue mana_flare;
        public static ForgeConfigSpec.DoubleValue endergized;
        public static ForgeConfigSpec.DoubleValue enderphins;
        public static ForgeConfigSpec.DoubleValue life_steal;
        public static ForgeConfigSpec.DoubleValue armor_pierce;
        public static ForgeConfigSpec.DoubleValue overheal;
        public static ForgeConfigSpec.DoubleValue summon_boost;
        public static ForgeConfigSpec.DoubleValue spell_exhaustion;
        public static ForgeConfigSpec.DoubleValue disarming;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("server");
            vulnerable = builder.
                    comment("Damage multiplier of vulnerable")
                    .defineInRange("vulnerable", .05, 0, Double.MAX_VALUE);
            exposed = builder.
                    comment("Damage multiplier of exposed")
                    .defineInRange("exposed", .05, 0, Double.MAX_VALUE);

            archery = builder.
                    comment("Damage multiplier of archery")
                    .defineInRange("archery", .05, 0, Double.MAX_VALUE);
            aiming = builder.
                    comment("Damage multiplier of aiming")
                    .defineInRange("aiming", .05, 0, Double.MAX_VALUE);

            marksman = builder.
                    comment("Damage multiplier of marksman")
                    .defineInRange("marksman", .05, 0, Double.MAX_VALUE);

            inspired = builder.
                    comment("Critical hit chance boost of inspired")
                    .defineInRange("might", .05, 0, 100);
            precise = builder.
                    comment("Critical hit chance boost of precise")
                    .defineInRange("precise", .05, 0, 100);

            brutality = builder.
                    comment("Critical hit damage boost of brutality")
                    .defineInRange("brutality", .05, 0, Double.MAX_VALUE);
            savage = builder.
                    comment("Critical hit damage boost of savage")
                    .defineInRange("savage", .05, 0, Double.MAX_VALUE);

            archers_frenzy = builder.
                    comment("Draw speed boost of archer's frenzy")
                    .defineInRange("archers_frenzy", .05, 0, Double.MAX_VALUE);
            hasty = builder.
                    comment("Draw speed boost of hasty")
                    .defineInRange("hasty", .05, 0, Double.MAX_VALUE);

            empowered = builder.
                    comment("Spell power boost of empowered")
                    .defineInRange("empowered", .05, 0, Double.MAX_VALUE);
            magic_up = builder.
                    comment("Spell power boost of magic up")
                    .defineInRange("magic_up", .05, 0, Double.MAX_VALUE);
            arcane_boost = builder.
                    comment("Spell power boost of arcane boost")
                    .defineInRange("arcane_boost", .05, 0, Double.MAX_VALUE);

            spell_haste = builder.
                    comment("Spell cooldown reduction boost of spell haste")
                    .defineInRange("spell_haste", 5d, 0, 100);

            focused = builder.
                    comment("Spell cast time reduction boost of focused")
                    .defineInRange("focused", 5d, 0, 100);

            arcanic_conversion = builder.
                    comment("Boost ender spell power by x for each point of attack * projectile_attack attribute")
                    .defineInRange("arcanic_conversion", .05, 0, Double.MAX_VALUE);

            arcanic_overload = builder.
                    comment("Physical Damage (ranged/melee) has an additional x damage (multiplicative) added as magic damage")
                    .defineInRange("arcanic_overload", .05, 0, Double.MAX_VALUE);

            retribution = builder.
                    comment("Deals x damage (additive) to the attacker")
                    .defineInRange("retribution", 1, 0, Double.MAX_VALUE);

            domineering = builder.
                    comment("Deal x more damage (multiplicative) on enemies under a negative status effect.")
                    .defineInRange("domineering", .05, 0, Double.MAX_VALUE);

            marked = builder.
                    comment("Increases the damage of the next physical damage by x (multiplicative). Immediately disappears after a successful attack.")
                    .defineInRange("marked", .05, 0, Double.MAX_VALUE);

            mana_flare = builder.
                    comment("Deals x damage (multiplicative with spell power) every second.")
                    .defineInRange("mana_flare", 1, 0, Double.MAX_VALUE);

            endergized = builder.
                    comment("Increases ender spell power by x per level")
                    .defineInRange("endergized", .05, 0, Double.MAX_VALUE);

            enderphins = builder.
                    comment("Increases ender spell power by x per level")
                    .defineInRange("enderphins", .05, 0, Double.MAX_VALUE);

            life_steal = builder.
                    comment("x physical damage dealt (multiplicative) is converted into health ")
                    .defineInRange("life_steal", .05, 0, Double.MAX_VALUE);

            armor_pierce = builder
                    .comment("x armor penetration per level (additive)")
                    .defineInRange("armor_pierce",1,0,Double.MAX_VALUE);

            overheal = builder
                    .comment("Increases the attributeslib:overheal attribute by x (multiplicative), which converts damage into absorption hearts at full health.")
                    .defineInRange("overheal",.05,0,Double.MAX_VALUE);

            summon_boost = builder
                    .comment("Increases the irons_spellbooks:summon_damage attribute by x (multiplicative) per level")
                    .defineInRange("summon_boost",.05,0,Double.MAX_VALUE);

            spell_exhaustion = builder
                    .comment("Reduces spell power by x (multiplicative) per level")
                    .defineInRange("spell_exhaustion",.05,0,1);

            disarming = builder
                    .comment("Reduces projectile damage by x (multiplicative) per level")
                    .defineInRange("disarming",.05,0,1);

            builder.pop();
        }
    }
}
