import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/** MY CLASS CALCULATOR***/

public class Calculator extends JFrame
{

    private JTextField my_textfield1;
    private JTextField my_textfield2;
    private JRadioButton my_radiobutton[];
    private JRadioButtonMenuItem view_rbs[];
    private JButton my_standard_buttons[];
    private JButton my_scientific_buttons[];
    private JButton my_control_buttons[];
    private JButton my_hexa_buttons[];
    private JMenuBar menubar;
    private Container container;
    private String command_line;
    private boolean decimal_point;
    private boolean start_operation;
    private double result;
    Color myown1=new Color(175,220,236);
    Color myown2=new Color(175,220,236);
    Color myown3=new Color(81,215,141);
    Color myown4=new Color(204,0,0);

    /**CALCULATOR CLASS CONSTRUCTOR*/

    public Calculator(String s)
    {
        super(s);

        container=getContentPane();
        container.setLayout(new BorderLayout());

        result=0.0;
        command_line="=";
        decimal_point=true;
        start_operation=true;

        setBounds(150,150,500,500);       //setting window size
        setResizable(true);               //setting window not resizeable
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setting_menubar();                //Function for making menubar
        making_buttons();				  //Function for making the screen and buttons
        setVisible(true);
    }

    /** SETTING THE MENUBAR***/

    public void setting_menubar()
    {
        menubar=new JMenuBar();

        // File Menu

        JMenu file=new JMenu("File");
        file.setMnemonic('F');

        JMenuItem exit=new JMenuItem("Exit");
        exit.setMnemonic('x');

        exit.setAccelerator(KeyStroke.getKeyStroke('X',Event.CTRL_MASK));
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        file.add(exit);
        menubar.add(file);

        //View Menu

        ButtonGroup rbs=new ButtonGroup();
        JMenu view=new JMenu("View");
        view.setMnemonic('V');

        String s[]={"Basic","Scientific"};
        view_rbs=new JRadioButtonMenuItem[s.length];


        for(int i=0;i<2;i++)
        {
            view_rbs[i]=new JRadioButtonMenuItem(s[i]);
            view_rbs[i].addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e)
                {
                    String s=e.getActionCommand();
                    String f;
                    if(s=="Basic")
                    {
                        view_rbs[0].setSelected(true);
                        f=s;
                        show(f);

                    }

                    else if(s=="Scientific")

                    {
                        view_rbs[1].setSelected(true);
                        f=s;
                        show(f);
                    }
                }
            });
            view_rbs[0].setSelected(true);
            rbs.add(view_rbs[i]);

            view.add(view_rbs[i]);
        }

        menubar.add(view);


        //ABOUT AUTHOR Menu
        JMenu about_me=new JMenu("CREATOR");
        JMenuItem about=new JMenuItem("About");
        about_me.setMnemonic('A');
        about.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(Calculator.this,
                        "DSU \n\n JAVA MINI PROJECT",
                        "CREATOR",JOptionPane.PLAIN_MESSAGE);
            }
        });
        about_me.add(about);
        menubar.add(about_me);
        setJMenuBar(menubar);
    }

    /**CALCULATOR VIEW MODE FUNCTION*/


    public void show(String s)
    {
        if(s=="Basic")
        {
            for(int i=0;i<my_scientific_buttons.length;i++)
            {
                my_scientific_buttons[i].setEnabled(false);
            }
        }
        else if(s=="Scientific")
        {
            for(int i=0;i<my_scientific_buttons.length;i++)
            {
                my_scientific_buttons[i].setEnabled(true);
            }
        }
    }

    /**CALCULATOR'S SCREEN FUNCTION INCLUDING SCREENS,BUTTONS ETC*/

    public void making_buttons()
    {
        JPanel common_screen_panel=new JPanel();  //A COMMON PANEL FOR THE BUTTONS

        JPanel screen=new JPanel();  // PANEL FOR THE SCREEN
        screen.setLayout(new GridLayout(1,1));
        my_textfield1=new JTextField(20);
        my_textfield1.setFont(new Font("Arial",Font.BOLD,14));
        my_textfield1.setBackground(Color.white);
        my_textfield1.setBorder(new CompoundBorder(BorderFactory.createEtchedBorder(Color.darkGray,Color.black),
                BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
        my_textfield1.setEditable(false);
        my_textfield1.setText("0");
        my_textfield1.setHorizontalAlignment(JTextField.RIGHT);
        screen.add(my_textfield1);

        container.add(screen,BorderLayout.NORTH);


        JPanel screen2 = new JPanel();  // PANEL FOR THE SOUTHERN SCREEN
        my_textfield2 = new JTextField(20);
        my_textfield2.setBackground(Color.black);
        my_textfield2.setForeground(Color.white);
        my_textfield2.setText("Error messages are displayed here");
        my_textfield2.setEditable(false);
        screen2.add(my_textfield2);
        container.add(screen2,BorderLayout.SOUTH);


        JPanel rb_panel = new JPanel();  // PANEL FOR THE RADIO BUTTONS
        rb_panel.setLayout(new GridLayout(1,3,2,2));
        rb_panel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));
        ButtonGroup rbg = new ButtonGroup();
        String b5[] = {"HEX","DEC","OCT","BINARY"};
        my_radiobutton= new JRadioButton[b5.length];

        for(int i=0;i< b5.length;i++)
        {
            my_radiobutton[i]= new JRadioButton(b5[i]);
            rbg.add(my_radiobutton[i]);
            my_radiobutton[i].setVisible(true);
            rb_panel.add(my_radiobutton[i]);

            my_radiobutton[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    String s = e.getActionCommand();
                    action_performed(s);
                }
            });


            common_screen_panel.add(rb_panel);
        }
        my_radiobutton[1].setSelected(true);



        JPanel button_panel=new JPanel(new GridLayout(3,1,5,5));//PANEL FOR CONTROL BUTTONS
        String b1[]={"CE","C","<-"};

        my_control_buttons=new JButton[b1.length];
        for(int i=0;i<b1.length;i++)
        {
            my_control_buttons[i]=new JButton(b1[i]);
            my_control_buttons[i].setForeground(Color.white);
            my_control_buttons[i].setBackground(myown4);
            my_control_buttons[i].setBorder(new CompoundBorder(BorderFactory.createEtchedBorder(Color.black,Color.white),BorderFactory.createRaisedBevelBorder()));
            my_control_buttons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    String s=e.getActionCommand();
                    if(s=="CE")
                    {
                        CE();
                    }
                    if(s=="C")
                    {
                        C();
                    }
                    if(s=="<-")
                    {
                        back();
                    }

                }});


            button_panel.add(my_control_buttons[i]);

            common_screen_panel.add(button_panel);
        }

        JPanel standard_panel=new JPanel(); //PANEL FOR THE STANDARD BUTTONS
        standard_panel.setLayout(new GridLayout(4,6,3,3));



        String b2 [] = {"MC","7","8","9","/","sqrt","MR","4","5","6","*","%",
                "MS","1","2","3","-","x!","mod","0","+/-",".","+","="};

        my_standard_buttons=new JButton[b2.length];

        for (int j=0;j<b2.length;j++)
        {
            my_standard_buttons[j]=new JButton(b2[j]);
            my_standard_buttons[j].setForeground(myown4);
            my_standard_buttons[j].setBackground(Color.pink);
            my_standard_buttons[j].setVisible(true);
            my_standard_buttons[j].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event)
                {
                    String s=event.getActionCommand();
                    action_performed(s);
                }

            });

            standard_panel.add(my_standard_buttons[j]);

            common_screen_panel.add(standard_panel);


        }



        JPanel scientific_panel=new JPanel();//PANEL FOR THE SCIENTIFIC KEYS
        scientific_panel.setLayout(new GridLayout(4,3,3,3));
        scientific_panel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));
        String b3[]={"sin(deg)","sin(rad)","exp","x^y","cos(deg)","cos(rad)","log","x^3","tan(deg)","tan(rad)","pi","x^2","(kg)BMI(m)","nPr","nCr"};
        my_scientific_buttons=new JButton[b3.length];

        for(int k=0;k<b3.length;k++)
        {
            my_scientific_buttons[k]=new JButton(b3[k]);
            my_scientific_buttons[k].setForeground(Color.black);
            my_scientific_buttons[k].setBackground(myown3);
            my_scientific_buttons[k].setEnabled(false);
            my_scientific_buttons[k].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    String s=e.getActionCommand();
                    action_performed(s);
                }});

            scientific_panel.add(my_scientific_buttons[k]);

            common_screen_panel.add(scientific_panel);
        }




        JPanel hexa_panel=new JPanel();  //PANEL FOR MY HEXA KEYS
        hexa_panel.setLayout(new GridLayout(1,6,3,3));
        hexa_panel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));
        String b4[]={"A","B","C","D","E","F"};
        my_hexa_buttons=new JButton[b4.length];

        for(int l=0;l<b4.length;l++)
        {
            my_hexa_buttons[l]=new JButton(b4[l]);
            my_hexa_buttons[l].setForeground(Color.white);
            my_hexa_buttons[l].setBackground(Color.black);
            my_hexa_buttons[l].setEnabled(false);
            my_hexa_buttons[l].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    String s=e.getActionCommand();
                    action_performed(s);
                }
            });

            hexa_panel.add(my_hexa_buttons[l]);

            common_screen_panel.add(hexa_panel);
        }


        container.add(common_screen_panel,BorderLayout.CENTER);
 	        	
 	        	/*ADDING ALL OF MY PANELS TO A COMMON PANEL AND THEN ADDING IT TO THE 
 	        	CONTAINER WITH THE ALLIGNMENT CENTRE SO THAT ALL THE PANEL CAN BE ADJUSTED EASILY IN THE WINDOW*/
    }
    /**CALCULATOR'S BACK KEY FUNCTION*/


    private void back()
    {
        String undo = my_textfield1.getText();
        int lengthy = undo.length();
        if (lengthy <= 1)
            my_textfield1.setText("0");
        else
        {
            char Backspace []= new char[lengthy-1];
            undo.getChars(0,lengthy-1,Backspace, 0);
            my_textfield1.setText("");
            for (int i=0;i<lengthy-1; i++)
                my_textfield1.setText(my_textfield1.getText() + Backspace[i]);
        }
    }

    /**CALCULATOR CLEAR-ALL FUNCTION*/

    private void C()
    {
        my_textfield1.setText("0");
        start_operation = true;
        decimal_point = true;
        command_line= "=";
        result = 0.0;
    }

    /**CALCULATOR CLEAR FUNCTION*/

    private void CE(){
        my_textfield1.setText("0");
        start_operation = true;
        decimal_point = true;
    }



    /**CALCULATOR'S ACTION FUNCTION*/



    public void action_performed (String s)
    {
        if (s=="0" ||
                s=="1" ||
                s=="2" ||
                s=="3" ||
                s=="4" ||
                s=="5" ||
                s=="6" ||
                s=="7" ||
                s=="8" ||
                s=="9" ||
                s==".")
            entering_digits(s);

        else if (s=="/"   ||
                s=="%"   ||
                s=="*"   ||
                s=="+"   ||
                s=="-"   ||
                s=="="   ||
                s=="sqrt"||
                s=="x!" ||
                s=="sin(deg)" ||
                s=="sin(rad)" ||
                s=="exp" ||
                s=="x^y" ||
                s=="cos(rad)" ||
                s=="cos(deg)" ||
                s=="log" ||
                s=="x^3" ||
                s=="tan(rad)" ||
                s=="tan(deg)" ||
                s=="pi"  ||
                s=="x^2" ||
                s=="(kg)BMI(m)" ||
                s=="nCr"  ||
                s=="nPr"  ||
                s=="MR"  ||
                s=="MS"  ||
                s=="mod"  ||
                s=="MC"   )
            doing_command(s);

        else if (s=="HEX"  ||
                s=="DEC"   ||
                s=="OCT"   ||
                s=="BINARY" ||
                s=="DEG")
            numbers_command(s);
    }

    /**CALCULATOR'S ENETERING DATA FUNCTION*/

    private void entering_digits(String inputs)
    {
        if (start_operation){
            my_textfield1.setText("");
            start_operation = false;
        }
        if(inputs=="."){
            if(decimal_point == true){
                my_textfield1.setText(my_textfield1.getText() + inputs);
                decimal_point = false;
            }
            else return;
        }
        else my_textfield1.setText(my_textfield1.getText() + inputs);
    }

    /**CALCULATOR'S DO COMMAND FUNCTION*/

    private void doing_command(String inputs)
    {
        calculate(Double.parseDouble(my_textfield1.getText()));
        command_line = inputs;
        start_operation = true;
    }


    /**CALCULATOR'S NUMBER MODE FUNCTION*/

    private void numbers_command(String input)
    {
        if (decimal_point == false)
        {
            return;
        }
        if  (input.equals("HEX"))
        {
            for (int j=0;j<my_hexa_buttons.length;j++)
                my_hexa_buttons[j].setEnabled(true);
            for (int j=0;j<my_scientific_buttons.length;j++)
                my_scientific_buttons[j].setEnabled(false);
            for (int j=0;j<my_standard_buttons.length;j++)
                my_standard_buttons[j].setEnabled(true);
            for (int j=0;j<my_standard_buttons.length;j++)
            {
                if (j==1 ||
                        j==2 ||
                        j==3 ||
                        j==5 ||
                        j==7 ||
                        j==8 ||
                        j==9 ||
                        j==11||
                        j==13||
                        j==14||
                        j==15||
                        j==17||
                        j==19)
                    continue;
                my_standard_buttons[j].setEnabled(false);




            }

        }

        if  (input.equals("DEC"))
        {
            for (int j=0;j<my_hexa_buttons.length;j++)
                my_hexa_buttons[j].setEnabled(false);
            for (int j=0;j<my_scientific_buttons.length;j++)
                my_scientific_buttons[j].setEnabled(true);
            for (int j=0;j<my_standard_buttons.length;j++)
                my_standard_buttons[j].setEnabled(true);
        }

        if  (input.equals("OCT"))
        {
            for (int j=0;j<my_standard_buttons.length;j++)
                my_standard_buttons[j].setEnabled(true);
            for (int j=0;j<my_hexa_buttons.length;j++)
                my_hexa_buttons[j].setEnabled(false);
            for (int j=0;j<my_standard_buttons.length;j++)
            {
                if ((j==1)  ||
                        (j==7)  ||
                        (j==8)  ||
                        (j==9)  ||
                        (j==13) ||
                        (j==14) ||
                        (j==15) ||
                        (j==19)  )
                    continue;
                my_standard_buttons[j].setEnabled(false);

            }
            for (int j=0;j<my_scientific_buttons.length;j++)
                my_scientific_buttons[j].setEnabled(false);
        }

        if  (input.equals("BINARY"))
        {
            for (int j=0;j<my_hexa_buttons.length;j++)
                my_hexa_buttons[j].setEnabled(false);
            for (int j=0;j<my_standard_buttons.length;j++)
            {
                if (j==13)
                    continue;
                if (j==19)
                    continue;
                my_standard_buttons[j].setEnabled(false);
            }
            for (int j=0;j<my_scientific_buttons.length;j++)
                my_scientific_buttons[j].setEnabled(false);
        }


        command_line = input;
        start_operation = true;

    }


    /**CALCULATOR'S CALCULATION FUNCTION*/

    private void calculate(double x)
    {
        if (command_line=="=")
        {
            result = x;
        }
        else if (command_line=="+")
        {
            result += x;
        }
        else if (command_line=="-")
        {
            result -=x;
        }
        else if (command_line=="*")
        {
            result *= x;
        }
        else if (command_line=="/")
        {
            result /= x;
        }
        else if (command_line=="%")
        {
            result = x/100;
        }
        else if (command_line=="sqrt") result = Math.sqrt(x);
        else if (command_line=="x!") {
            int r=1, i;
            for(i=1; i<=x; i++) {
                r=r*i;
            }
            result = r;
        }
        else if (command_line=="sin(rad)") result = Math.sin(x);
        else if (command_line=="sin(deg)") result = Math.sin((x*3.1415926535897932384)/180);
        else if (command_line=="exp") result = Math.exp(x);
        else if (command_line=="x^y") result = Math.pow(result, x);
        else if (command_line=="cos(rad)") result = Math.cos(x);
        else if (command_line=="cos(deg)") result = Math.cos((x*3.1415926535897932384)/180);
        else if (command_line=="log") result = Math.log(x);
        else if (command_line=="x^3") result = x*x*x;
        else if (command_line=="tan(rad)") result = Math.tan(x);
        else if (command_line=="tan(deg)") result = Math.tan((x*3.1415926535897932384)/180);
        else if (command_line=="pi") result = 3.1415926535897932384;
        else if (command_line=="x^2") result = x*x;
        else if (command_line=="(kg)BMI(m)") result = ((result)/(x*x));
        else if (command_line=="nPr") {
            int r=1, s=1, i;
            for(i=1; i<=result; i++) {
                r=r*i;
            }
            for(i=1; i<=(result-x); i++) {
                s=s*i;
            }
            result=r/s;
        }
        else if (command_line=="nCr") {
            int r=1, s=1, p=1, i;
            for(i=1; i<=result; i++) {
                r=r*i;
            }
            for(i=1; i<=(result-x); i++) {
                s=s*i;
            }
            for(i=1; i<=x; i++) {
                p=p*i;
            }
            result=r/(s*p);
        }
        else if(command_line=="MR") result=Double.parseDouble(my_textfield1.getText());
        else if(command_line=="MS") result=Double.parseDouble(my_textfield1.getText());
        else if(command_line=="mod") result = result % x;
        else if(command_line=="MC") result=Double.parseDouble(my_textfield1.getText());

        my_textfield1.setText("" + result);


        start_operation = true;
        decimal_point = true;
    }



    /**MAIN FUNCTION***/




    public static void main(String [] args)
    {

        new Calculator("MY CALCULATOR");

    }
}