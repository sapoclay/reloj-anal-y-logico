import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Reloj extends JPanel {
    private Timer timer;
    private JLabel fechaLabel;
    private JLabel horaDigitalLabel;
    private JPanel separador;
    
    public Reloj() {
        setPreferredSize(new Dimension(400, 500));
        setBackground(new Color(245, 245, 245)); // Fondo gris claro moderno
        
        // Panel para las etiquetas
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(3, 1, 0, 5)); // espacio entre componentes
        labelPanel.setBackground(new Color(245, 245, 245));
        
        // Crear label para la fecha
        fechaLabel = new JLabel();
        fechaLabel.setHorizontalAlignment(JLabel.CENTER);
        fechaLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Fuente más moderna
        fechaLabel.setForeground(new Color(51, 51, 51)); // Gris oscuro
        
        // Crear separador
        separador = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(200, 200, 200));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(100, getHeight()/2, getWidth()-100, getHeight()/2);
            }
        };
        separador.setPreferredSize(new Dimension(getWidth(), 10));
        separador.setBackground(new Color(245, 245, 245));
        
        // Crear label para la hora digital
        horaDigitalLabel = new JLabel();
        horaDigitalLabel.setHorizontalAlignment(JLabel.CENTER);
        horaDigitalLabel.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Fuente 
        horaDigitalLabel.setForeground(new Color(41, 128, 185)); // Azul 
        
        labelPanel.add(fechaLabel);
        labelPanel.add(separador);
        labelPanel.add(horaDigitalLabel);
        
        setLayout(new BorderLayout());
        add(labelPanel, BorderLayout.SOUTH);
        
        // el timer para actualizar cada segundo
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                actualizarFechaYHora();
                repaint();
            }
        }, 0, 1000);
    }
    
    private void actualizarFechaYHora() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        fechaLabel.setText(ahora.format(formatterFecha));
        horaDigitalLabel.setText(ahora.format(formatterHora));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Obtener el tiempo actual
        LocalDateTime tiempo = LocalDateTime.now();
        int hora = tiempo.getHour();
        int minuto = tiempo.getMinute();
        int segundo = tiempo.getSecond();
        
        // calcular el centro del panel
        int centerX = getWidth() / 2;
        int centerY = (getHeight() - 100) / 2;
        int radio = Math.min(centerX, centerY) - 20;
        
        // el círculo exterior del reloj
        g2d.setColor(new Color(41, 128, 185));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval(centerX - radio, centerY - radio, radio * 2, radio * 2);
        
        // las marcas de minutos
        for (int i = 0; i < 60; i++) {
            double angulo = Math.toRadians(6 * i - 90);
            int longitud = (i % 5 == 0) ? 15 : 7;
            int radioExterno = radio;
            int radioInterno = radio - longitud;
            
            int x1 = (int) (centerX + radioExterno * Math.cos(angulo));
            int y1 = (int) (centerY + radioExterno * Math.sin(angulo));
            int x2 = (int) (centerX + radioInterno * Math.cos(angulo));
            int y2 = (int) (centerY + radioInterno * Math.sin(angulo));
            
            g2d.setStroke(new BasicStroke(i % 5 == 0 ? 2 : 1));
            g2d.drawLine(x1, y1, x2, y2);
        }
        
        // los números
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 18));
        g2d.setColor(new Color(51, 51, 51));
        for (int i = 1; i <= 12; i++) {
            double angulo = Math.toRadians(30 * i - 90);
            int x = (int) (centerX + (radio - 35) * Math.cos(angulo));
            int y = (int) (centerY + (radio - 35) * Math.sin(angulo));
            String numero = String.valueOf(i);
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(numero, x - fm.stringWidth(numero)/2, y + fm.getHeight()/3);
        }
        
        // las manecillas con efecto de sombra
        // Hora
        double anguloHora = Math.toRadians((hora % 12 + minuto / 60.0) * 30 - 90);
        dibujarManecillaConSombra(g2d, centerX, centerY, anguloHora, radio * 0.5, 4, new Color(51, 51, 51));
        
        // Minuto
        double anguloMinuto = Math.toRadians(minuto * 6 - 90);
        dibujarManecillaConSombra(g2d, centerX, centerY, anguloMinuto, radio * 0.7, 3, new Color(51, 51, 51));
        
        // Segundo
        double anguloSegundo = Math.toRadians(segundo * 6 - 90);
        dibujarManecillaConSombra(g2d, centerX, centerY, anguloSegundo, radio * 0.8, 1, new Color(231, 76, 60));
        
        // Dibujar punto central con efecto 3D
        g2d.setColor(new Color(41, 128, 185));
        g2d.fillOval(centerX - 8, centerY - 8, 16, 16);
        g2d.setColor(new Color(52, 152, 219));
        g2d.fillOval(centerX - 6, centerY - 6, 12, 12);
    }
    
    private void dibujarManecillaConSombra(Graphics2D g2d, int centerX, int centerY, double angulo, double longitud, float grosor, Color color) {
        // Dibujar sombra
        g2d.setColor(new Color(0, 0, 0, 30));
        g2d.setStroke(new BasicStroke(grosor + 2));
        int x2 = (int) (centerX + longitud * Math.cos(angulo));
        int y2 = (int) (centerY + longitud * Math.sin(angulo));
        g2d.drawLine(centerX + 2, centerY + 2, x2 + 2, y2 + 2);
        
        // Dibujar manecilla
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(centerX, centerY, x2, y2);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Reloj AnalYLógico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Reloj());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}