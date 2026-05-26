
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraApp extends JFrame implements ActionListener {

    JTextField txtNum1, txtNum2;
    JLabel lblResultadoMath;

    JTextField txtTemp;
    JLabel lblResultadoTemp;

    JTextField txtMoneda;
    JLabel lblResultadoMoneda;

    double tasaCambio = 3800;

    public CalculadoraApp() {

        setTitle("Calculadora");
        setSize(500, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 5, 5));

        // ---- PANEL MATEMATICAS ----
        JPanel panelMath = new JPanel();
        panelMath.setLayout(new FlowLayout());
        panelMath.setBorder(BorderFactory.createTitledBorder("Operaciones Matematicas"));

        panelMath.add(new JLabel("Numero 1:"));
        txtNum1 = new JTextField(8);
        panelMath.add(txtNum1);

        panelMath.add(new JLabel("Numero 2:"));
        txtNum2 = new JTextField(8);
        panelMath.add(txtNum2);

        JButton btnSumar = new JButton("Sumar");
        JButton btnRestar = new JButton("Restar");
        JButton btnMultiplicar = new JButton("Multiplicar");
        JButton btnDividir = new JButton("Dividir");

        btnSumar.setActionCommand("SUMAR");
        btnRestar.setActionCommand("RESTAR");
        btnMultiplicar.setActionCommand("MULTIPLICAR");
        btnDividir.setActionCommand("DIVIDIR");

        btnSumar.addActionListener(this);
        btnRestar.addActionListener(this);
        btnMultiplicar.addActionListener(this);
        btnDividir.addActionListener(this);

        panelMath.add(btnSumar);
        panelMath.add(btnRestar);
        panelMath.add(btnMultiplicar);
        panelMath.add(btnDividir);

        lblResultadoMath = new JLabel("Resultado: ");
        panelMath.add(lblResultadoMath);

        // ---- PANEL TEMPERATURA ----
        JPanel panelTemp = new JPanel();
        panelTemp.setLayout(new FlowLayout());
        panelTemp.setBorder(BorderFactory.createTitledBorder("Conversion de Temperatura"));

        panelTemp.add(new JLabel("Temperatura:"));
        txtTemp = new JTextField(8);
        panelTemp.add(txtTemp);

        JButton btnCaF = new JButton("Celsius a Fahrenheit");
        JButton btnFaC = new JButton("Fahrenheit a Celsius");

        btnCaF.setActionCommand("C_A_F");
        btnFaC.setActionCommand("F_A_C");

        btnCaF.addActionListener(this);
        btnFaC.addActionListener(this);

        panelTemp.add(btnCaF);
        panelTemp.add(btnFaC);

        lblResultadoTemp = new JLabel("Resultado: ");
        panelTemp.add(lblResultadoTemp);

        // ---- PANEL MONEDA ----
        JPanel panelMoneda = new JPanel();
        panelMoneda.setLayout(new FlowLayout());
        panelMoneda.setBorder(BorderFactory.createTitledBorder("Conversion de Moneda (1 USD = $3800 COP)"));

        panelMoneda.add(new JLabel("Valor:"));
        txtMoneda = new JTextField(8);
        panelMoneda.add(txtMoneda);

        JButton btnUSDaCOP = new JButton("Dolares a Pesos");
        JButton btnCOPaUSD = new JButton("Pesos a Dolares");

        btnUSDaCOP.setActionCommand("USD_COP");
        btnCOPaUSD.setActionCommand("COP_USD");

        btnUSDaCOP.addActionListener(this);
        btnCOPaUSD.addActionListener(this);

        panelMoneda.add(btnUSDaCOP);
        panelMoneda.add(btnCOPaUSD);

        lblResultadoMoneda = new JLabel("Resultado: ");
        panelMoneda.add(lblResultadoMoneda);

        add(panelMath);
        add(panelTemp);
        add(panelMoneda);

        setVisible(true);
    }

    // Verifica que el texto solo tenga numeros, punto decimal y opcionalmente signo negativo
    public boolean esNumeroValido(String texto) {
        // Solo permite: digitos, un punto, y un signo menos al inicio
        return texto.matches("-?\\d+(\\.\\d+)?");
    }

    // Verifica que el texto solo tenga numeros y punto (sin negativo, para moneda)
    public boolean esNumeroPositivo(String texto) {
        return texto.matches("\\d+(\\.\\d+)?");
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("SUMAR") || cmd.equals("RESTAR") ||
            cmd.equals("MULTIPLICAR") || cmd.equals("DIVIDIR")) {
            operacionMatematica(cmd);
        }

        if (cmd.equals("C_A_F") || cmd.equals("F_A_C")) {
            convertirTemperatura(cmd);
        }

        if (cmd.equals("USD_COP") || cmd.equals("COP_USD")) {
            convertirMoneda(cmd);
        }
    }

    public void operacionMatematica(String operacion) {

        if (txtNum1.getText().isEmpty() || txtNum2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa los dos numeros");
            return;
        }

        // Validar que no tenga caracteres especiales
        if (!esNumeroValido(txtNum1.getText()) || !esNumeroValido(txtNum2.getText())) {
            JOptionPane.showMessageDialog(this, "Solo se permiten numeros. No uses letras ni caracteres especiales como @, #, $, %, etc.");
            return;
        }

        double num1 = Double.parseDouble(txtNum1.getText());
        double num2 = Double.parseDouble(txtNum2.getText());
        double resultado = 0;

        if (operacion.equals("SUMAR")) {
            resultado = num1 + num2;
        }

        if (operacion.equals("RESTAR")) {
            resultado = num1 - num2;
        }

        if (operacion.equals("MULTIPLICAR")) {
            resultado = num1 * num2;
        }

        if (operacion.equals("DIVIDIR")) {
            if (num2 == 0) {
                JOptionPane.showMessageDialog(this, "No se puede dividir entre cero");
                return;
            }
            resultado = num1 / num2;
        }

        lblResultadoMath.setText("Resultado: " + resultado);
        JOptionPane.showMessageDialog(this, "El resultado es: " + resultado);
    }

    public void convertirTemperatura(String tipo) {

        if (txtTemp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa una temperatura");
            return;
        }

        // Validar que no tenga caracteres especiales
        if (!esNumeroValido(txtTemp.getText())) {
            JOptionPane.showMessageDialog(this, "Solo se permiten numeros. No uses letras ni caracteres especiales como @, #, $, %, etc.");
            return;
        }

        double temp = Double.parseDouble(txtTemp.getText());
        double resultado = 0;

        if (tipo.equals("C_A_F")) {
            resultado = (temp * 9 / 5) + 32;
            lblResultadoTemp.setText("Resultado: " + resultado + " F");
            JOptionPane.showMessageDialog(this, temp + " C = " + resultado + " F");
        }

        if (tipo.equals("F_A_C")) {
            resultado = (temp - 32) * 5 / 9;
            lblResultadoTemp.setText("Resultado: " + resultado + " C");
            JOptionPane.showMessageDialog(this, temp + " F = " + resultado + " C");
        }
    }

    public void convertirMoneda(String tipo) {

        if (txtMoneda.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un valor");
            return;
        }

        // Validar que no tenga caracteres especiales ni negativos
        if (!esNumeroPositivo(txtMoneda.getText())) {
            JOptionPane.showMessageDialog(this, "Solo se permiten numeros positivos. No uses letras, caracteres especiales ni numeros negativos.");
            return;
        }

        double valor = Double.parseDouble(txtMoneda.getText());
        double resultado = 0;

        if (tipo.equals("USD_COP")) {
            resultado = valor * tasaCambio;
            lblResultadoMoneda.setText("Resultado: $" + resultado + " COP");
            JOptionPane.showMessageDialog(this, valor + " USD = $" + resultado + " COP");
        }

        if (tipo.equals("COP_USD")) {
            resultado = valor / tasaCambio;
            lblResultadoMoneda.setText("Resultado: $" + resultado + " USD");
            JOptionPane.showMessageDialog(this, valor + " COP = $" + resultado + " USD");
        }
    }

    public static void main(String[] args) {
        new CalculadoraApp();
    }
}
