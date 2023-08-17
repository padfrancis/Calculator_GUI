package Calculator_GUI;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Calc_Frame extends JFrame implements PropertyChangeListener
{
    ImageIcon img = new ImageIcon("src/Calculator/icon.png");
    Calc_Frame ()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 700);
        this.setLayout(null);
        this.setIconImage(img.getImage());
        this.setTitle("CaliCis+");
        this.setFont(new Font("Arial", Font.BOLD, 30));
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(59, 60, 54));
        this.add(new Panels());
        this.add(new Panels.TextPanel());
        Panels.TopPanel topPanel = new Panels.TopPanel(this);
        this.add(topPanel);
        this.setVisible(true);
    }
    public void setTheme(String theme) {
        switch (theme) {
            case "Light  ":
                getContentPane().setBackground(Color.WHITE);
                break;
            case "Dark":
                getContentPane().setBackground(new Color(59, 60, 54));
                break;
            default:
                break;
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {

    }
}
