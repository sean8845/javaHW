import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

public class CalculationForm {
    private JPanel calculationView;
    private JButton Button4;
    private JButton Button8;
    private JButton ButtonEqual;
    private JButton Button9;
    private JButton ButtonAdd;
    private JButton ButtonMultiply;
    private JButton ButtonMinus;
    private JButton Button6;
    private JButton Button3;
    private JButton buttonDot;
    private JButton Button5;
    private JButton Button2;
    private JButton Button0;
    private JButton Button1;
    private JButton ButtonMinusPlus;
    private JButton Button7;
    private JTextField displayField;
    private JButton ButtonCE;
    private JButton ButtonClear;
    private JButton ButtonDivide;

    enum CalcOP {NONE, ADD, SUB, MULTIPLY, DIVIDE}
    private boolean isDigitEnterMode = false;
    private String displayString = "";
    private double result = 0;
    private CalcOP lastOP = CalcOP.NONE;



    private void enterDigit(String digit)
    {
        if (!isDigitEnterMode) {
            if (digit == ".")
                displayString = "0.";
            else
                displayString = digit;
            isDigitEnterMode = true;
        }
        else {
// Only floating-point number
// can start with 0
            if (displayString == "0" && digit != ".")
                return;
            displayString += digit;
        }
        displayField.setText(displayString);
    }

    private void evalLastOP(CalcOP currOP)
    {
        double value = Double.parseDouble(displayField.getText());
// Note that we evaluate last Operator, not current
        try {
            switch (lastOP) {
                case ADD:
                    result += value;
                    break;
                case SUB:
                    result -= value;
                    break;
                case DIVIDE:
                    result /= value;
                    break;
                case MULTIPLY:
                    result *= value;
                    break;
                default: // First value
                    result = value;
                    break;
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        displayField.setText(Double.toString(result));
        isDigitEnterMode = false;
        lastOP = currOP;
    }


    public CalculationForm() {
        Button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("0");
            }
        });
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("1");
            }
        });
        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("2");
            }
        });
        Button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("3");
            }
        });
        Button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("4");
            }
        });
        Button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("5");
            }
        });
        Button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("6");
            }
        });
        Button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("7");
            }
        });
        Button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("8");
            }
        });
        Button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterDigit("9");
            }
        });
        ButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evalLastOP(CalcOP.ADD);
            }
        });
        ButtonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evalLastOP(CalcOP.SUB);
            }
        });
        ButtonMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evalLastOP(CalcOP.MULTIPLY);
            }
        });
        ButtonDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evalLastOP(CalcOP.DIVIDE);
            }
        });
        ButtonEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evalLastOP(CalcOP.NONE);
            }
        });
        ButtonMinusPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text=displayField.getText();
                if(text=="0.0")
                    return;
                    char fist_c=text.charAt(0);
                    if(fist_c=='-')
                        displayField.setText(text.substring(1));
                    else
                        displayField.setText("-"+text);
            }
        });
        ButtonCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDigitEnterMode=false;
                displayField.setText("0");
            }
        });
        ButtonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastOP=CalcOP.NONE;
                isDigitEnterMode=false;
                displayField.setText("0.0");
            }
        });
        buttonDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {enterDigit(".");}
        });
    }

    public void testClick(String button) throws Exception
    {
        switch (button)
        {
            case "+": ButtonAdd.doClick(); break;
            case "-": ButtonMinus.doClick(); break;
            case "*": ButtonMultiply.doClick(); break;
            case "/": ButtonDivide.doClick(); break;
            case ".": buttonDot.doClick(); break;
            case "=": ButtonEqual.doClick(); break;
            case "±": ButtonMinusPlus.doClick(); break;
            case "CE": ButtonCE.doClick(); break;
            case "CLEAR": ButtonClear.doClick(); break;
            case "0": Button0.doClick(); break;
            case "1": Button1.doClick(); break;
            case "2": Button2.doClick(); break;
            case "3": Button3.doClick(); break;
            case "4": Button4.doClick(); break;
            case "5": Button5.doClick(); break;
            case "6": Button6.doClick(); break;
            case "7": Button7.doClick(); break;
            case "8": Button8.doClick(); break;
            case "9": Button9.doClick(); break;
            default:
                throw new Exception("Error! No button " + button);
        }
    }

    public void showWindow() {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(this.calculationView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public double getResult() {
        return result;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CalculationForm");
        frame.setContentPane(new CalculationForm().calculationView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
