package tfar.moremobeffects;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public static final ModConfig.Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<Server, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(ModConfig.Server::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }


    public static class Server {
        public final ForgeConfigSpec.DoubleValue vulnerable;
        public final ForgeConfigSpec.DoubleValue exposed;
        public final ForgeConfigSpec.DoubleValue archery;
        public final ForgeConfigSpec.DoubleValue aiming;
        public final ForgeConfigSpec.DoubleValue marksman;

        public final ForgeConfigSpec.DoubleValue inspired;
        public final ForgeConfigSpec.DoubleValue precise;

        public final ForgeConfigSpec.DoubleValue brutality;
        public final ForgeConfigSpec.DoubleValue savage;

        public final ForgeConfigSpec.DoubleValue archers_frenzy;
        public final ForgeConfigSpec.DoubleValue hasty;

        public final ForgeConfigSpec.DoubleValue might;
        public final ForgeConfigSpec.DoubleValue strengthened;

        public final ForgeConfigSpec.DoubleValue empowered;
        public final ForgeConfigSpec.DoubleValue magic_up;
        public final ForgeConfigSpec.DoubleValue arcane_boost;

        public final ForgeConfigSpec.DoubleValue spell_haste;

        public final ForgeConfigSpec.DoubleValue focused;
        public final ForgeConfigSpec.DoubleValue arcanic_conversion;
        public final ForgeConfigSpec.DoubleValue arcanic_overload;
        public final ForgeConfigSpec.DoubleValue retribution;
        public final ForgeConfigSpec.DoubleValue domineering;
        public final ForgeConfigSpec.DoubleValue marked;
        public final ForgeConfigSpec.DoubleValue mana_flare_base;
        public final ForgeConfigSpec.DoubleValue mana_flare_spell_power_scaler;
        public final ForgeConfigSpec.DoubleValue endergized;
        public final ForgeConfigSpec.DoubleValue enderphins;
        public final ForgeConfigSpec.DoubleValue life_steal;
        public final ForgeConfigSpec.DoubleValue armor_pierce;
        public final ForgeConfigSpec.DoubleValue overheal;
        public final ForgeConfigSpec.DoubleValue summon_boost;
        public final ForgeConfigSpec.DoubleValue spell_exhaustion;
        public final ForgeConfigSpec.DoubleValue disarming;

        public final ForgeConfigSpec.DoubleValue enders_game_spell_power_decrease;
        public final ForgeConfigSpec.DoubleValue enders_game_spell_cooldown_decrease;
        public final ForgeConfigSpec.DoubleValue enders_game_spell_cast_time_decrease;

        public final ForgeConfigSpec.DoubleValue will_of_the_summoner;
        public final ForgeConfigSpec.DoubleValue battle_mage;
        public final ForgeConfigSpec.DoubleValue peak_health;

        public final ForgeConfigSpec.DoubleValue peak_health_min;

        public final ForgeConfigSpec.DoubleValue berserk_attack_damage;
        public final ForgeConfigSpec.DoubleValue berserk_ender_spell_power;

        public final ForgeConfigSpec.DoubleValue revive;
        public final ForgeConfigSpec.DoubleValue martyr;
        public final ForgeConfigSpec.DoubleValue sorcerous_transference;

        public final ForgeConfigSpec.DoubleValue aegis_absorption_hp_multiplier;
        public final ForgeConfigSpec.DoubleValue aegis_base_absorption;
        public final ForgeConfigSpec.DoubleValue aegis_damage_resistance;

        public final ForgeConfigSpec.DoubleValue blazing_aspect;
        public final ForgeConfigSpec.DoubleValue wolf_aspect;
        public final ForgeConfigSpec.DoubleValue withering_aspect;
        public final ForgeConfigSpec.DoubleValue sigil_of_mana;

        public final ForgeConfigSpec.DoubleValue beefy;
        public final ForgeConfigSpec.DoubleValue robust;
        public final ForgeConfigSpec.DoubleValue hardy;
        public final ForgeConfigSpec.DoubleValue swingy;
        public final ForgeConfigSpec.DoubleValue mana_regeneration;

        public final ForgeConfigSpec.DoubleValue injury;
        public final ForgeConfigSpec.DoubleValue fractured;

        public final ForgeConfigSpec.DoubleValue pledge_of_unity_max_dist;
        public final ForgeConfigSpec.DoubleValue pledge_of_unity_resistance;
        public final ForgeConfigSpec.DoubleValue pledge_of_unity_healing_received;
        public final ForgeConfigSpec.DoubleValue pledge_of_unity_lonely;

        public final ForgeConfigSpec.DoubleValue pledge_of_solitude_max_dist;
        public final ForgeConfigSpec.DoubleValue pledge_of_solitude_crit_damage;
        public final ForgeConfigSpec.DoubleValue pledge_of_solitude_crowded;


        public final ForgeConfigSpec.DoubleValue magical_fuse_power;
        public final ForgeConfigSpec.DoubleValue magical_fuse_damage_multiplier;

        public final ForgeConfigSpec.DoubleValue master_of_arms;

        public final ForgeConfigSpec.DoubleValue battleborn_attack_damage;
        public final ForgeConfigSpec.DoubleValue battleborn_ender_spell_power;
        public final ForgeConfigSpec.DoubleValue battleborn_resistance;

        public final ForgeConfigSpec.DoubleValue on_the_defensive_max_health;
        public final ForgeConfigSpec.DoubleValue on_the_defensive_max_mana;

        public final ForgeConfigSpec.DoubleValue guarded;

        public final ForgeConfigSpec.DoubleValue flighty_and_mighty_attack_speed;
        public final ForgeConfigSpec.DoubleValue flighty_and_mighty_movement_speed;

        public final ForgeConfigSpec.DoubleValue stunning_strike_damage_multiplier;
        public final ForgeConfigSpec.DoubleValue stunning_strike_stun_base_duration;
        public final ForgeConfigSpec.DoubleValue stunning_strike_duration_scaling;

        public final ForgeConfigSpec.DoubleValue hunted_target_damage_multiplier;
        public final ForgeConfigSpec.DoubleValue alchemical_transgression_damage_multiplier;

        public final ForgeConfigSpec.DoubleValue enduring_duration_reduction;
        public final ForgeConfigSpec.IntValue guarded_shield_timer;

        public final ForgeConfigSpec.DoubleValue corrosive_base_damage;
        public final ForgeConfigSpec.DoubleValue corrosive_scaling;

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

            might = builder.
                    comment("Strength variant modifier")
                    .defineInRange("might", 3, 0, Double.MAX_VALUE);
            strengthened = builder.
                    comment("Strength variant modifier")
                    .defineInRange("strengthened", 3, 0, Double.MAX_VALUE);

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

            mana_flare_base = builder.
                    comment("Deals x damage (multiplicative with spell power) every second, scales with level")
                    .defineInRange("mana_flare", 1, 0, Double.MAX_VALUE);

            mana_flare_spell_power_scaler = builder
                    .defineInRange("mana_flare_spell_power_scaler", 1, 0, Double.MAX_VALUE);

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

            builder.push("enders_game");

            enders_game_spell_power_decrease = builder
                    .comment("x Spell Power Reduction per level (multiplicative)")
                    .defineInRange("spell_power_decrease",.12,-100,100);
            enders_game_spell_cooldown_decrease = builder
                    .comment("x Cooldown Reduction per level (multiplicative)")
                    .defineInRange("spell_cooldown_decrease",.2,-100,100);
            enders_game_spell_cast_time_decrease = builder
                    .comment("x Cast Time Reduction per level (multiplicative)")
                    .defineInRange("cast_time_decrease",.1,-100,100);

            builder.pop();

            will_of_the_summoner = builder.
                    comment("Boost summoning damage by x per point of spell power + ender spell power")
                    .defineInRange("will_of_the_summoner", .05, 0, Double.MAX_VALUE);

            battle_mage = builder.
                    comment("Physical and Ranged attacks deal (x *  (ender spell power + spell power - 1) extra damage")
                    .defineInRange("battle_mage", .05, 0, Double.MAX_VALUE);

            peak_health = builder.
                    comment("Gain x per level of your Max Health boosts as Critical damage. (multiplicative)")
                    .defineInRange("peak_health", .05, 0, Double.MAX_VALUE);

            peak_health_min = builder.
                    comment("Minimum max health required to benefit from peak health.")
                    .defineInRange("peak_health_min", 20, 0, Double.MAX_VALUE);

            berserk_attack_damage = builder
                    .comment("Deal more melee damage the lower your health is. ")
                            .defineInRange("berserk_attack_damage",2.5,0,Double.MAX_VALUE);

            berserk_ender_spell_power = builder
                    .comment("Gain more ender spell power the lower your health is. ")
                    .defineInRange("berserk_ender_spell_power",2.5,0,Double.MAX_VALUE);

            sorcerous_transference = builder
                    .comment("When under this effect, the user’s melee/ranged attacks give the target a spell power buff " +
                            "based on x% of the user’s ender & general spell power (not the targets).")
                    .defineInRange("sorcerous_transference",.1,0,Double.MAX_VALUE);

            revive = builder.comment("When health reaches 0, revive with x% of your maximum health.")
                    .defineInRange("revive",.1,0,1);

            martyr = builder.comment("Redirects x% of all damage received from teammates to you.")
                    .defineInRange("martyr",.1,0,1);

            builder.push("aegis");

            aegis_absorption_hp_multiplier = builder.comment("Gain x% of your maximum health per level as absorption hearts.")
                            .defineInRange("absorption_hp_multiplier",1,0,Double.MAX_VALUE);

            aegis_base_absorption = builder.comment("Base amount of absorption given per level.")
                    .defineInRange("base_absorption",1,0,Double.MAX_VALUE);

            aegis_damage_resistance = builder.comment("Aegis damage resistance, does NOT scale with level, 1 blocks all damage")
                    .defineInRange("damage_resistance",.15,0,1);

            builder.pop();

            blazing_aspect = builder.comment("Deal x% additional crit damage per level to targets that are burning.\n")
                    .defineInRange("blazing_aspect",.1,0,Double.MAX_VALUE);

            wolf_aspect = builder.comment("Attacks have an x% additional crit chance per level to targets that are bleeding.")
                    .defineInRange("wolf_aspect",.1,0,Double.MAX_VALUE);

            withering_aspect = builder.comment("Attacks ignore x% of armor per level to targets that are under the wither effect.")
                    .defineInRange("withering_aspect",.1,0,100);

            sigil_of_mana = builder.comment("Attacks deal x% increased spell damage to targets that are under the Mana Flare effect.")
                    .defineInRange("sigil_of_mana",.1,0,100);

            beefy = builder.comment("Increase max health by x per level additively")
                            .defineInRange("beefy",4,0,Double.MAX_VALUE);

            robust = builder.comment("Increase max health by x per level multiplicatively")
                    .defineInRange("robust",.1,0,Double.MAX_VALUE);

            hardy = builder.comment("Increase max health by x per level multiplicatively")
                    .defineInRange("hardy",.1,0,Double.MAX_VALUE);

            swingy = builder.comment("Increase attack speed by x per level additively")
                            .defineInRange("swingy",1,0,Double.MAX_VALUE);

            mana_regeneration = builder.comment("Increases irons_spellbooks:mana_regen attribute additively")
                            .defineInRange("mana_regeneration",1,0,Double.MAX_VALUE);

            injury = builder.comment("Reduces max health by a flat amount per level.")
                    .defineInRange("injury",2,0,Double.MAX_VALUE);
            fractured = builder.comment("Reduces max health by a percentage amount per level.")
                    .defineInRange("fractured",.1,0,Double.MAX_VALUE);

            pledge_of_unity_max_dist = builder.comment("Max distance for pledge of unity")
                    .defineInRange("pledge_of_unity_max_dist",16,0,Double.MAX_VALUE);

            pledge_of_unity_resistance = builder.comment("Gain x (additive) moremobeffects:resistance per level when a team member is within y blocks of you")
                    .defineInRange("pledge_of_unity_resistance",.1,0,Double.MAX_VALUE);

            pledge_of_unity_healing_received = builder.comment("Gain x (additive) attributeslib:healing_received per level when a team member is within y blocks of you")
                    .defineInRange("pledge_of_unity_healing_received",.1,0,Double.MAX_VALUE);

            pledge_of_unity_lonely = builder.comment("Lose x (additive) moremobeffects:resistance when a team member is NOT within y blocks of you and while affected by pledge of unity")
                    .defineInRange("pledge_of_unity_lonely",.1,0,Double.MAX_VALUE);


            pledge_of_solitude_max_dist = builder.comment("Max distance for pledge of solitude")
                    .defineInRange("pledge_of_solitude_max_dist",16,0,Double.MAX_VALUE);

            pledge_of_solitude_crit_damage = builder.comment("Gain x (multiplicative) attributeslib:crit_damage per level when no team member is within x blocks of you.")
                    .defineInRange("pledge_of_solitude_resistance",.1,0,Double.MAX_VALUE);

            pledge_of_solitude_crowded = builder.comment("Lose x (additive) attributeslib:crit_rate when a team member is within y blocks of you")
                    .defineInRange("pledge_of_solitude_crowded",.2,0,Double.MAX_VALUE);


            magical_fuse_power = builder.comment("Explosion power of magical fuse")
                    .defineInRange("magical_fuse_power",5,0,Float.MAX_VALUE);

            magical_fuse_damage_multiplier = builder.comment("Damage multiplier of magical fuse")
                    .defineInRange("magical_fuse_damage_multiplier",1,0,Float.MAX_VALUE);

            master_of_arms = builder.comment("Add x amount (multiplicative) of attack_damage to moremobeffects:projectile_damage per level")
                    .defineInRange("master_of_arms",.1,0,Float.MAX_VALUE);

            battleborn_attack_damage = builder.comment("Gain x% generic.attack_damage when at OR above 50% of max HP")
                    .defineInRange("battleborn_attack_damage",.1,0,Float.MAX_VALUE);

            battleborn_ender_spell_power = builder.comment("Gain x (multiplicative) irons_spellbooks:ender_spell_damage when at OR above 50% of max HP")
                    .defineInRange("battleborn_ender_spell_power",.1,0,Float.MAX_VALUE);

            battleborn_resistance = builder.comment("Gain x (multiplicative) moremobeffects:resistance when below 50% of max HP.")
                    .defineInRange("battleborn_resistance",.5,0,Float.MAX_VALUE);

            on_the_defensive_max_health = builder.comment("Your next basic/ranged attack will grant you absorption hearts based on x% of your max health and y% mana.")
                    .defineInRange("on_the_defensive_max_health",1,0,Float.MAX_VALUE);

            on_the_defensive_max_mana = builder.comment("Your next basic/ranged attack will grant you absorption hearts based on x% of your max health and y% mana.")
                    .defineInRange("on_the_defensive_max_mana",.0625,0,Float.MAX_VALUE);

            guarded = builder.comment("Increase your damage resistance by x (multiplicative)")
                    .defineInRange("guarded",.125,0,Float.MAX_VALUE);

            flighty_and_mighty_attack_speed = builder.comment("generic.attack_speed is boosted by x " +
                            "(multiplicative) of your irons_spellbooks:cooldown_reduction")
                    .defineInRange("flighty_and_mighty_attack_speed",.125,0,Float.MAX_VALUE);

            flighty_and_mighty_movement_speed = builder.comment("generic.movement_speed is boosted by x " +
                            "(multiplicative) of your irons_spellbooks:cooldown_reduction")
                    .defineInRange("flighty_and_mighty_movement_speed",.125,0,Float.MAX_VALUE);

            stunning_strike_damage_multiplier = builder
                    .defineInRange("stunning_strike_damage_multiplier",.25,0,Float.MAX_VALUE);

            stunning_strike_stun_base_duration = builder
                    .defineInRange("stunning_strike_stun_base_duration",100,0,Float.MAX_VALUE);

            hunted_target_damage_multiplier = builder.defineInRange("hunted_target_damage_multiplier",.25,0,Float.MAX_VALUE);

            alchemical_transgression_damage_multiplier = builder.defineInRange("alchemical_transgression_damage_multiplier",.25,0,Float.MAX_VALUE);

            enduring_duration_reduction = builder.comment("Percentage to reduce duration of harmful effects per level").defineInRange("enduring_duration_reduction",.20,0,1);

            guarded_shield_timer = builder.comment("How long guarded absorption lasts").defineInRange("guarded_shield_timer",200,0,100000000);

            corrosive_base_damage = builder.comment("Corrosive base damage").defineInRange("corrosive_base_damage",1,0,1e38f);

            corrosive_scaling = builder.comment("Corrosive spellpower scaling").defineInRange("corrosive_scaling",1,-1e38f,1e38f);

            stunning_strike_duration_scaling = builder.comment("Stun strike duration scaling").defineInRange("stunning_strike_duration_scaling",1,0,1e9);

            builder.pop();
        }
    }
}
