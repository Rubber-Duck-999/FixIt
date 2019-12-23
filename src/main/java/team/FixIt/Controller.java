package team.FixIt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

public class Controller implements ActionListener
{
    public Model _model;
    public View _view;

    public Controller(Model m, View v)
    {
        this._model = m;
        this._view = v;
    }

    public void enterCommand()
    {
        Integer val = _model.checkPass();
    }

    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String input = e.getActionCommand();
        checkAction(input);
    }

    public void checkAction(String input)
    {
        
    }

    public void initmodel(int x)
    {
        //_view.setDigits(_model.initModel(x));
    }
}