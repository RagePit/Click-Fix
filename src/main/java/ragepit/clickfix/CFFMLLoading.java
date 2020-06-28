package ragepit.clickfix;
import java.util.Map;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@MCVersion(value = "1.8.9")
public class CFFMLLoading implements IFMLLoadingPlugin {
	
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{CFClassTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return CFClassTransformer.class.getName();
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
