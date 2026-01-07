package Elio.view.model;

import Elio.model.Element;
import Elio.view.Viewer;

public abstract class ElementViewer extends Viewer<Element> {
    public ElementViewer(Element model) {
        super(model);
    }
}
