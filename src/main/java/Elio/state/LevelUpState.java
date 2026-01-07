package Elio.state;

import Elio.controller.Controller;
import Elio.controller.LevelUpController;
import Elio.model.Arena;
import Elio.model.LevelUpChoice;
import Elio.model.Menu;
import Elio.view.LevelUpViewer;
import Elio.view.Viewer;

import java.util.ArrayList;
import java.util.List;

public class LevelUpState extends State<Menu> {
    private final List<LevelUpChoice> options;
    private final Arena stats;
    private final int levels;

    //same thing as menu state
    public LevelUpState(Menu model, Arena stats, int levels){
        super(model);
        this.options = createOptions();
        this.stats = stats;
        this.levels = levels;
        init();
    }

    private List<LevelUpChoice> createOptions(){
        List<LevelUpChoice> options = new ArrayList<>();
        int optionsNeeded = 3;

        while ( optionsNeeded > 0 ){
            LevelUpChoice option = new LevelUpChoice(4-optionsNeeded);
            if (!options.contains(option)) {
                options.add(option);
                optionsNeeded--;
            }
        }

        return options;
    }

    @Override
    protected Controller<Menu> createController() { return new LevelUpController(getModel(), stats, options, levels); }

    @Override
    protected Viewer<Menu> createViewer() {
        return new LevelUpViewer(getModel(), stats, options);
    }

    //for testing purposes
    public List<LevelUpChoice> getOptions() {
        return options;
    }
}