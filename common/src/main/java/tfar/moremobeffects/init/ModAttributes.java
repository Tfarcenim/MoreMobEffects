package tfar.moremobeffects.init;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ModAttributes {

    public static final Attribute PROJECTILE_ATTACK_DAMAGE = new RangedAttribute("attribute.name.generic.projectile_attack_damage", 1, 0,65536).setSyncable(true);
    public static final Attribute RESISTANCE = new RangedAttribute("attribute.name.generic.resistance",1, -2048,2).setSyncable(true);

}
