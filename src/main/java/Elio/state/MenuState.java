package Elio.state;

import Elio.model.Menu;

public abstract class MenuState extends State<Menu> {
    public MenuState(Menu menu){
        super(menu);
    }
}
