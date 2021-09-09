package View;

import Tools.ResizingTracker;

import javax.swing.*;
import java.awt.*;

public class ToolsBar extends JScrollPane {

    GridBagConstraints gc = new GridBagConstraints();
    JPanel content;

    /**
     * The differents categories of pictures selectionned
     */
    public JCheckBox people, places, school;

    /**
     * The tools bar, contains tools to filter the pictures
     */
    public ToolsBar(){
        super(new JPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.content = (JPanel) this.getViewport().getView();
        this.content.setLayout(new GridBagLayout());
        setTools();
        this.setName("ScrollToolBar");
        new ResizingTracker(this, true, true);
    }

    private void setTools(){
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(10,10,10,10);
        JLabel info1 = new JLabel("<html><u>Categories of photos<u/><html/>");
        this.content.add(info1, gc);
        people = new JCheckBox("People");
        gc.gridy++;
        gc.insets = new Insets(5,10,5,10);
        this.content.add(people, gc);
        places = new JCheckBox("Places");
        gc.gridy++;
        this.content.add(places, gc);
        school = new JCheckBox("School");
        gc.gridy++;
        this.content.add(school, gc);

    }

    /**
     * Return a String containing the list of the selected options
     * @return
     */
    public String getSelectedOption(){
        String str = "[";
        int nbSelect = 0;
        if(people.isSelected()){
            str += "Peoples; ";
            nbSelect++;
        }
        if(places.isSelected()){
            str+= "Places; ";
            nbSelect++;
        }
        if(school.isSelected()){
            str+= "School; ";
            nbSelect++;
        }
        if(nbSelect == 0){
            str += "No option selected";
        }
        str += "]";
        return str;

    }


}
