package club.deltapvp.deltacore.api.utilities.builder.itembuilder;

import lombok.Getter;
import lombok.Setter;

public abstract class APIItemBuilder {

    @Getter @Setter
    private static APIItemBuilder instance;

    public abstract AbstractVersionItemBuilder getVersionItemBuilder();

}
