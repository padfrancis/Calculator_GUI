package Calculator_GUI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import static Calculator_GUI.Panels.TextPanel.b;
public class Panels extends JPanel implements ActionListener
{
    static Color pColor = new Color(32, 43, 57);
    static Border border = new RoundBorder(new Color(32, 43, 57), 2, 20, 20);
    static int borderThickness = 2;
    static JTextField text = new JTextField();
    static JPanel pan = new JPanel();
    static double num1=0,num2=0,result=0;
    static char operator;
    static String[] funcTexts = { "AC", "DEL", "(-)", "/","x","-", "+", "00", ".", "="};
    static JButton[] buttons = new JButton[10];
    static JButton[] functions = new JButton[10];
    static Component a;

    {
        a = this;
    }

    Panels()
    {
        this.setBackground(pColor);
        //this.setOpaque(false);
        this.setLayout(new GridLayout(5, 4, 10, 10));
        this.setPreferredSize(new Dimension(450 - 2 * borderThickness, 410 - 2 * borderThickness));
        this.setBounds(15, 220, 450, 410);
        this.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setButtons();
        this.add(functions[0]);
        this.add(functions[1]);
        this.add(functions[2]);
        this.add(functions[3]);
        this.add(buttons[7]);
        this.add(buttons[8]);
        this.add(buttons[9]);
        this.add(functions[4]);
        this.add(buttons[4]);
        this.add(buttons[5]);
        this.add(buttons[6]);
        this.add(functions[5]);
        this.add(buttons[1]);
        this.add(buttons[2]);
        this.add(buttons[3]);
        this.add(functions[6]);
        this.add(functions[7]);
        this.add(buttons[0]);
        this.add(functions[8]);
        this.add(functions[9]);
    }
    void setButtons()
    {
        for (int i = 0; i < buttons.length; i++)
        {
            buttons[i] = createCustomButton(String.valueOf(i), new Color(57, 59, 87), new Dimension(0, 0));
            buttons[i].addActionListener(this);
        }
        for (int j = 0; j < functions.length; j++)
        {
            functions[j] = createCustomButton(funcTexts[j], new Color(57, 59, 87), new Dimension(0,0));
            functions[j].addActionListener(this);
        }
    }
    static JButton createCustomButton(String text, Color bgColor, Dimension size)
    {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setPreferredSize(size);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFocusPainted(false);

        button.setUI(new CustomButtonUI());
        /*
        Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        button.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
         */
        return button;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < buttons.length; i++)
        {
            if(TextPanel.text.getText().equals("ERROR"))
            {
                TextPanel.text.setText("");
            }
            if (e.getSource()==buttons[i])
            {
                if(TextPanel.text.getText().length() > 10)
                {
                    TextPanel.text.setText("TOO LONG!");
                }
                else
                {
                    if(TextPanel.text.getText().equals("TOO LONG!"))
                    {
                        TextPanel.text.setText("");
                    }
                    TextPanel.text.setText(TextPanel.text.getText().concat(String.valueOf(i)));
                }
            }
        }
        if (e.getSource()==functions[8])
        {
            if(TextPanel.text.getText().equals("ERROR") || TextPanel.text.getText().equals("TOO LONG!"))
            {
                TextPanel.text.setText("");
            }
            else
            {
                TextPanel.text.setText(TextPanel.text.getText().concat("."));
            }
        }
        if (e.getSource()==functions[7])
        {
            if(TextPanel.text.getText().equals("ERROR") || TextPanel.text.getText().equals("TOO LONG!"))
            {
                TextPanel.text.setText("");
            }
            else
            {
                TextPanel.text.setText(TextPanel.text.getText().concat("00"));
            }
        }
        if(e.getSource()==functions[2])
        {
            String num;
            if(TextPanel.text.getText().equals(""))
            {
                try
                {
                    num = "";
                }
                catch (Exception d)
                {
                    System.out.println("Error");
                }
                finally
                {
                    TextPanel.text.setText("ERROR");
                }
            }
            else
            {
                double neg = Double.parseDouble(TextPanel.text.getText());
                neg*=(-1);
                TextPanel.text.setText(String.valueOf(neg));
            }
        }
        if (e.getSource()==functions[3])
        {
            num1 = Double.parseDouble(TextPanel.text.getText());
            operator = '/';
            TextPanel.text.setText("");
        }
        if (e.getSource()==functions[4])
        {
            num1 = Double.parseDouble(TextPanel.text.getText());
            operator = 'x';
            TextPanel.text.setText("");
        }
        if (e.getSource()==functions[5])
        {
            num1 = Double.parseDouble(TextPanel.text.getText());
            operator = '-';
            TextPanel.text.setText("");
        }
        if (e.getSource()==functions[6])
        {
            num1 = Double.parseDouble(TextPanel.text.getText());
            operator = '+';
            TextPanel.text.setText("");
        }
        if (e.getSource()==functions[9])
        {
            DecimalFormat decimal = new DecimalFormat("#.##");
            num2 = Double.parseDouble(TextPanel.text.getText());
            if(TextPanel.text.getText().equals("ERROR"))
            {
                TextPanel.text.setText("");
            }
            else
            {
                switch (operator)
                {
                    case '/':
                        if(num1 == 0 || num2 == 0)
                        {
                            TextPanel.text.setText("ERROR");
                        }
                        result = num1 / num2;
                        break;
                    case 'x':
                        result = num1 * num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '+':
                        result = num1 + num2;
                        break;
                    default:
                        break;
                }
                TextPanel.text.setText(String.valueOf(result));
                num1 = result;
            }
        }
        if (e.getSource()==functions[0])
        {
            num1=0;
            num2=0;
            result=0;
            TextPanel.text.setText("");
        }
        if(e.getSource()==functions[1])
        {
            String string = TextPanel.text.getText();
            TextPanel.text.setText("");
            for(int j=0; j < string.length()-1; j++)
            {
                TextPanel.text.setText(TextPanel.text.getText()+string.charAt(j));
            }
        }
    }
    //{ "AC", "DEL", "%", "/","x","-", "+", "00", ".", "="};
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g.create();
        //g2.setColor(pColor);
        double borderThickness = 2;
        g2.fillRect((int) borderThickness, (int) borderThickness, (int) (getWidth() - 2 * borderThickness), (int) (getHeight() - 2 * borderThickness));
        g2.dispose();
        super.paintComponent(g);
    }
    @Override
    protected void paintBorder(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(0,0,0,0));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(4));
        int arcWidth = 25;
        int arcHeight = 25;
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2.dispose();
    }
    public static class TextPanel extends JPanel implements DocumentListener {
        static Component b;

        {
            b = this;
        }
        static Color pColor = new Color(32, 43, 57);
        static Border border = new RoundBorder(new Color(32, 43, 57), 2, 20, 20);
        static int borderThickness = 2;
        static JTextField text = new JTextField();
        int limit = 10;

        TextPanel()
        {
            this.setBackground(pColor);
            //this.setOpaque(false);
            this.setBounds(15, 90, 450, 120);
            this.setPreferredSize(new Dimension(450 - 2 * borderThickness, 150 - 2 * borderThickness));
            this.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            setText();
            this.setLayout(new GridLayout(1,1));
            this.add(text);
            this.setVisible(true);
        }
        void setText()
        {
            text.setOpaque(true);
            text.setForeground(Color.WHITE);
            text.setBackground(pColor);
            text.setHorizontalAlignment(SwingConstants.RIGHT);
            text.setEditable(false);
            text.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
            text.getDocument().addDocumentListener(this);
        }

        private void handleTextLimit() {
            /*
            String texts = text.getText();
            if (texts.length() > limit) {
                text.setText(texts.substring(0, limit));
            } else {
                // Hide the message label when the text is within the limit
            }
            */
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.fillRect((int) borderThickness, (int) borderThickness, (int) (getWidth() - 2 * borderThickness), (int) (getHeight() - 2 * borderThickness));
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(0, 0, 0, 0));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(4));
            int arcWidth = 25;
            int arcHeight = 25;
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
            g2.dispose();
        }
        @Override
        public void insertUpdate(DocumentEvent e) {
            handleTextLimit();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleTextLimit();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            handleTextLimit();
        }
    }
    static class TopPanel extends JPanel implements ActionListener
    {
        private Calc_Frame calcFrame;
        private JRadioButton[] themeRadios;
        private ButtonGroup group = new ButtonGroup();
        private JLabel label = new JLabel("  CaliCis+                       ");
        int index;

        TopPanel(Calc_Frame calcFrame)
        {
            this.calcFrame = calcFrame;
            this.setOpaque(false);
            this.setVisible(true);
            this.setBounds(0, 20, 500, 50);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 30));
            setRadButtons();
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.add(label);
            for (JRadioButton radio : themeRadios)
            {
                this.add(radio);
            }
        }

        void setRadButtons(){
            String[] themes = {"Light  ", "Dark"};
            themeRadios = new JRadioButton[themes.length];
            for (int i = 0; i < themes.length; i++)
            {
                themeRadios[i] = new JRadioButton(themes[i]);
                themeRadios[i].setFocusable(false);
                themeRadios[i].setHorizontalAlignment(SwingConstants.RIGHT);
                themeRadios[i].setFont(new Font("Arial", Font.BOLD, 16));
                themeRadios[i].addActionListener(this);
                //themeRadios[i].setOpaque(false);
                themeRadios[i].setBackground(new Color(59, 60, 54));
                group.add(themeRadios[i]);
            }
            themeRadios[1].setSelected(true);
            themeRadios[0].setForeground(Color.WHITE);
            themeRadios[1].setForeground(Color.WHITE);
            themeRadios[0].addActionListener(e -> calcFrame.setTheme("Light  "));
            themeRadios[1].addActionListener(e -> calcFrame.setTheme("Dark"));
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (JRadioButton radio : themeRadios)
            {
                if (radio.isSelected())
                {
                    String theme = radio.getText();
                    if(theme.equals("Light  "))
                    {
                        label.setForeground(Color.BLACK);
                        for (JButton button : buttons)
                        {
                            button.setBackground(Color.white);
                            button.setForeground(Color.BLACK);
                        }
                        for (JButton function : functions)
                        {
                            function.setBackground(Color.white);
                            function.setForeground(Color.BLACK);
                        }
                        a.setBackground(new Color(250, 246, 232));
                        b.setBackground(new Color(250, 246, 232));
                        text.setForeground(Color.WHITE);
                        text.setBackground(new Color(250, 246, 232));
                        themeRadios[0].setForeground(Color.BLACK);
                        themeRadios[1].setForeground(Color.BLACK);
                        themeRadios[0].setBackground(Color.WHITE);
                        themeRadios[1].setBackground(Color.WHITE);
                    }
                    else
                    {
                        label.setForeground(Color.WHITE);
                        for (JButton button : buttons) {
                            button.setBackground(new Color(57, 59, 87));
                            button.setForeground(Color.WHITE);
                        }
                        for (JButton function : functions) {
                            function.setBackground(new Color(57, 59, 87));
                            function.setForeground(Color.WHITE);
                        }
                        a.setBackground(pColor);
                        b.setBackground(pColor);
                        themeRadios[0].setForeground(Color.WHITE);
                        themeRadios[1].setForeground(Color.WHITE);
                        themeRadios[0].setBackground(new Color(59, 60, 54));
                        themeRadios[1].setBackground(new Color(59, 60, 54));
                    }
                }
            }
        }
    }
}