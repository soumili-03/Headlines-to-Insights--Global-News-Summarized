
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.json.JSONObject;

public class JavaGUIJFrame extends javax.swing.JFrame {

    public JavaGUIJFrame() {
        initComponents();
        Summarize_Text.setVisible(false);
        Translate_Text.setVisible(false);
        Convert_Text_To_Speech.setVisible(false);
        Summarize_and_translate.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        Extract_Text = new javax.swing.JButton();
        Summarize_Text = new javax.swing.JButton();
        Translate_Text = new javax.swing.JButton();
        Convert_Text_To_Speech = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        responseTextArea = new javax.swing.JTextArea();
        Summarize_and_translate = new javax.swing.JButton();
        outputLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setEnabled(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Extract_Text.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Extract_Text.setText("Extract Text");
        Extract_Text.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Extract_Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Extract_TextActionPerformed(evt);
            }
        });
        jPanel1.add(Extract_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 94, 33));

        Summarize_Text.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Summarize_Text.setText("Summarize");
        Summarize_Text.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Summarize_Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Summarize_TextActionPerformed(evt);
            }
        });
        jPanel1.add(Summarize_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 108, 33));

        Translate_Text.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Translate_Text.setText("Translate");
        Translate_Text.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Translate_Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Translate_TextActionPerformed(evt);
            }
        });
        jPanel1.add(Translate_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 93, 33));

        Convert_Text_To_Speech.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Convert_Text_To_Speech.setText("Convert Text to Speech");
        Convert_Text_To_Speech.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Convert_Text_To_Speech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Convert_Text_To_SpeechActionPerformed(evt);
            }
        });
        jPanel1.add(Convert_Text_To_Speech, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 233, 160, 40));

        responseTextArea.setColumns(20);
        responseTextArea.setRows(5);
        responseTextArea.setEditable(false);
        responseTextArea.setLineWrap(true);
        responseTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(responseTextArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 430, 240));

        Summarize_and_translate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Summarize_and_translate.setText("Summarize and Translate");
        Summarize_and_translate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Summarize_and_translate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Summarize_and_translateActionPerformed(evt);
            }
        });
        jPanel1.add(Summarize_and_translate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 180, 33));

        outputLabel.setBackground(new java.awt.Color(255, 255, 255));
        outputLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        outputLabel.setForeground(new java.awt.Color(255, 255, 255));
        outputLabel.setText("Output");
        jPanel1.add(outputLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("English to Marathi Text Translation");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 300, 40));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundImage.jpg"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseEntered

    private void Summarize_and_translateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Summarize_and_translateActionPerformed
        // TODO add your handling code here:
        //String text = inputTextField.getText();
        new Thread(() -> {
            try {
                @SuppressWarnings("deprecation")
                URL url = new URL("http://127.0.0.1:8080/summarize_and_translate");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                //                String jsonInputString = "{\"text\": \"" + text + "\"}";
                //                try (OutputStream os = connection.getOutputStream()) {
                //                    byte[] input = jsonInputString.getBytes("utf-8");
                //                    os.write(input, 0, input.length);
                //                }
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();

                    saveToFile(response.toString(), "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\translated_summary_text.txt", true);

                    SwingUtilities.invokeLater(() -> responseTextArea.setText(response.toString()));
                } else {
                    SwingUtilities.invokeLater(() -> responseTextArea.setText("Error: " + responseCode));
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> responseTextArea.setText("Exception: " + e.getMessage()));
            }
        }).start();
    }//GEN-LAST:event_Summarize_and_translateActionPerformed

    private void Convert_Text_To_SpeechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Convert_Text_To_SpeechActionPerformed
        // TODO add your handling code here:
        //String text = inputTextField.getText();
        // String preprocessedText = preprocessText(text);
        new Thread(() -> {
            try {
                javax.sound.sampled.AudioInputStream audioInputStream = null;
                @SuppressWarnings("deprecation")
                URL url = new URL("http://127.0.0.1:8080/convert_to_speech"); // Change the URL to the translation endpoint
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                //connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                //                String jsonInputString = "{\"text\": \"" + text + "\"}";
                //                try (OutputStream os = connection.getOutputStream()) {
                //                    byte[] input = jsonInputString.getBytes("utf-8");
                //                    os.write(input, 0, input.length);
                //                }
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    java.io.InputStream inputStream = connection.getInputStream();
                    try {
                        AudioSystem.getAudioInputStream(inputStream);
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    }

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();

                    saveToFile(response.toString(), "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\translated_text.txt", true);

                    // play the audio
                    try {

                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();

                        // clean up resources
                        inputStream.close();
                        audioInputStream.close();
                    } catch (LineUnavailableException e) {
                        SwingUtilities.invokeLater(() -> responseTextArea.setText("LineUnavailableException: " + e.getMessage()));
                    }

                } else {
                    SwingUtilities.invokeLater(() -> responseTextArea.setText("Error: " + responseCode));
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> responseTextArea.setText("Exception: " + e.getMessage()));
            }
        }).start();
    }//GEN-LAST:event_Convert_Text_To_SpeechActionPerformed

    private void Translate_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Translate_TextActionPerformed
        // TODO add your handling code here:
        // String text = inputTextField.getText();
        // String preprocessedText = preprocessText(text);
        new Thread(() -> {
            try {
                @SuppressWarnings("deprecation")
                URL url = new URL("http://127.0.0.1:8080/translate"); // Change the URL to the translation endpoint
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                //                String jsonInputString = "{\"text\": \"" + text + "\"}";
                //                try (OutputStream os = connection.getOutputStream()) {
                //                    byte[] input = jsonInputString.getBytes("utf-8");
                //                    os.write(input, 0, input.length);
                //                }
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();

                    saveToFile(response.toString(), "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\translated_text.txt", true);

                    Point textAreaLocation = responseTextArea.getLocationOnScreen();
                    openNotepadPosition(textAreaLocation, "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\translated_text.txt");
                    SwingUtilities.invokeLater(() -> responseTextArea.setText(response.toString()));
                } else {
                    SwingUtilities.invokeLater(() -> responseTextArea.setText("Error: " + responseCode));
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> responseTextArea.setText("Exception: " + e.getMessage()));
            }
        }).start();
    }//GEN-LAST:event_Translate_TextActionPerformed

    private void openNotepadPosition(Point location, String filePath) {
        if (Desktop.isDesktopSupported()) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);

                    File file = new File(filePath);
                    Desktop.getDesktop().open(file);
                    System.out.println("TextArea Location: " + location.x + ", " + (location.y + responseTextArea.getHeight() + 10));
                    // get notepad window
                    for (Window window : Window.getWindows()) {
                        if (window instanceof Frame) {
                            Frame frame = (Frame) window;
                            if (frame.getTitle().equals(filePath)) {
                                frame.setLocation(location.x, location.y + responseTextArea.getHeight() + 10);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    private void Summarize_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Summarize_TextActionPerformed

        //String text = inputTextField.getText();
        // String preprocessedText = preprocessText(text);
        new Thread(() -> {
            try {
                @SuppressWarnings("deprecation")
                URL url = new URL("http://127.0.0.1:8080/summarize");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                //String jsonInputString = "{\"text\": \"" + text + "\"}";
                //                try (OutputStream os = connection.getOutputStream()) {
                //                    byte[] input = jsonInputString.getBytes("utf-8");
                //                    os.write(input, 0, input.length);
                //                }
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();
                    saveToFile(response.toString(), "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\summarized_text.txt", false);
                    SwingUtilities.invokeLater(() -> responseTextArea.setText(response.toString()));
                } else {
                    SwingUtilities.invokeLater(() -> responseTextArea.setText("Error: " + responseCode));
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> responseTextArea.setText("Exception: " + e.getMessage()));
            }
        }).start();
    }//GEN-LAST:event_Summarize_TextActionPerformed

    private void Extract_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Extract_TextActionPerformed

        //String serverUrl = "http://127.0.0.1:8080/extract_text"; // Change this to your Flask server URL
        // String filePath = "./Dashboard.png"; // Change this to the path of your image
        // file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Image File: ");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File imageFile = fileChooser.getSelectedFile();
            String serverUrl = "http://127.0.0.1:8080/extract_text";

            try {
                URL url = new URL(serverUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                String boundary = "Boundary-" + System.currentTimeMillis();
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                OutputStream outputStream = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

                // Send image file
                writer.append("--" + boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + imageFile.getName() + "\"")
                        .append("\r\n");
                writer.append("Content-Type: image/jpeg").append("\r\n"); // Change Content-Type accordingly
                writer.append("\r\n").flush();
                FileInputStream inputStream = new FileInputStream(imageFile);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                inputStream.close();
                writer.append("\r\n").flush();

                writer.append("--" + boundary + "--").append("\r\n");
                writer.close();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JSONObject jsonObject = new JSONObject(response.toString());
                    // responseTextArea.setText(response.toString());
                    responseTextArea.setText(jsonObject.getString("extracted_text"));

                    System.out.println("Response: " + response.toString());
                    Summarize_Text.setVisible(true);
                    Translate_Text.setVisible(true);
                    Convert_Text_To_Speech.setVisible(true);
                    Summarize_and_translate.setVisible(true);
                } else {
                    System.out.println("POST request failed with response code " + responseCode);
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_Extract_TextActionPerformed

    public void saveToFile(String text, String fileName, boolean openFile) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(text);
            writer.close();
            System.out.println("File Saved");

            if (openFile) {
                File file = new File(fileName);
                Desktop.getDesktop().open(file);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving file." + e.getMessage());
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JavaGUIJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Convert_Text_To_Speech;
    private javax.swing.JButton Extract_Text;
    private javax.swing.JButton Summarize_Text;
    private javax.swing.JButton Summarize_and_translate;
    private javax.swing.JButton Translate_Text;
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextArea responseTextArea;
    // End of variables declaration//GEN-END:variables
}
