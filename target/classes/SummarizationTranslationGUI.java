// MAIN CODE

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;


import java.io.FileWriter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;
import org.json.JSONObject;

public class SummarizationTranslationGUI extends JFrame {
  private JTextArea responseTextArea;
  private JTextField inputTextField;

  public SummarizationTranslationGUI() {
    try {

      PlainDocument document = new PlainDocument();
      document.setDocumentFilter(new UTF8DocumentFilter());
      setTitle("Summarizer and Translator");
      setSize(600, 400);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new BorderLayout());

      JPanel inputPanel = new JPanel();
      JLabel inputLabel = new JLabel("Input Text:");
      inputTextField = new JTextField(30);
      inputTextField.setDocument(document);
      inputTextField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));

      inputPanel.add(inputLabel, BorderLayout.WEST);
      inputPanel.add(inputTextField, BorderLayout.CENTER);
      add(inputPanel, BorderLayout.NORTH);

      JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

      JButton summarizeButton = new JButton("Summarize");
      summarizeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          summarizeButton();
        }
      });

      JButton translateButton = new JButton("Translate");
      translateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          translateButtonClicked();
        }
      });

      JButton summarize_translateButton = new JButton("Summarize and Translate");
      summarize_translateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          summarize_and_translate();
        }
      });

      JButton convertTextToSpeechButton = new JButton("Text to Speech");
      convertTextToSpeechButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          textToSpeechButtonClicked();
        }
      });

      JButton extractTextButton = new JButton("Extract Text from Image");
      extractTextButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JFileChooser fileChooser = new JFileChooser();
          fileChooser.setDialogTitle("Choose an image file");
          fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));
          int userSelection = fileChooser.showOpenDialog(SummarizationTranslationGUI.this);

          if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            extractTextButtonClicked(selectedFile);
          }
        }
      });

      buttonPanel.add(extractTextButton);
      buttonPanel.add(summarizeButton);
      buttonPanel.add(translateButton);
      buttonPanel.add(summarize_translateButton);
      buttonPanel.add(convertTextToSpeechButton);
      add(buttonPanel, BorderLayout.CENTER);

      // response text area becomes scrollable after pressing the summarize button
      // because the length of the text returned by the server may exceed the visible
      // area
      // of the responseTextArea
      // so swing automatically adds a vertical scrollbar to allow the use to scroll
      // through text

      // load the font (ignore this part)
      responseTextArea = new JTextArea();
      String fontFileName = "D:\\TANVI_COLLEGE_FILES\\PROGRAMMING SCRIPTS\\PERSONAL PROJECTS\\PBLtril\\src\\main\\java\\fonts\\Shivaji01 Normal.ttf";
      InputStream is = this.getClass().getResourceAsStream(fontFileName);
      Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, is);
      Font ttfReal = ttfBase.deriveFont(Font.PLAIN, 23);

      // responseTextArea.setLocale(Locale.forLanguageTag("hi"));

      //responseTextArea.setFont(new Font("Shivaji01", Font.BOLD, 23));
       responseTextArea.setFont(ttfReal);
      //responseTextArea.setFont(new Font("Arial", Font.PLAIN, 12));

      responseTextArea.setEditable(false);
      responseTextArea.setLineWrap(true); // to enable line wrapping
      responseTextArea.setWrapStyleWord(true); // wrap at word boundaries

      JScrollPane scrollPane = new JScrollPane(responseTextArea);
      add(scrollPane, BorderLayout.SOUTH);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }

  }

  // one button to do it all
  private void summarize_and_translate() {
    String text = inputTextField.getText();
    new Thread(() -> {
      try {
        @SuppressWarnings("deprecation")
        URL url = new URL("http://127.0.0.1:5000/summarize_and_translate");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = "{\"text\": \"" + text + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
          byte[] input = jsonInputString.getBytes("utf-8");
          os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          StringBuilder response = new StringBuilder();
          String line;
          while ((line = in.readLine()) != null) {
            response.append(line);
          }
          in.close();

          saveToFile(response.toString(), "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\translated_summary_text.txt");

          SwingUtilities.invokeLater(() -> responseTextArea.setText(response.toString()));
        } else {
          SwingUtilities.invokeLater(() -> responseTextArea.setText("Error: " + responseCode));
        }
      } catch (IOException e) {
        SwingUtilities.invokeLater(() -> responseTextArea.setText("Exception: " + e.getMessage()));
      }
    }).start();
  }

  private void summarizeButton() {
    String text = inputTextField.getText();
    // String preprocessedText = preprocessText(text);
    new Thread(() -> {
      try {
        @SuppressWarnings("deprecation")
        URL url = new URL("http://127.0.0.1:5000/summarize");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = "{\"text\": \"" + text + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
          byte[] input = jsonInputString.getBytes("utf-8");
          os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          StringBuilder response = new StringBuilder();
          String line;
          while ((line = in.readLine()) != null) {
            response.append(line);
          }
          in.close();
          saveToFile(response.toString(), "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\summarized_text.txt");
          SwingUtilities.invokeLater(() -> responseTextArea.setText(response.toString()));
        } else {
          SwingUtilities.invokeLater(() -> responseTextArea.setText("Error: " + responseCode));
        }
      } catch (IOException e) {
        SwingUtilities.invokeLater(() -> responseTextArea.setText("Exception: " + e.getMessage()));
      }
    }).start();
  }

  private void translateButtonClicked() {
    String text = inputTextField.getText();
    // String preprocessedText = preprocessText(text);
    new Thread(() -> {
      try {
        @SuppressWarnings("deprecation")
        URL url = new URL("http://127.0.0.1:5000/translate"); // Change the URL to the translation endpoint
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = "{\"text\": \"" + text + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
          byte[] input = jsonInputString.getBytes("utf-8");
          os.write(input, 0, input.length);
        }
        
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          StringBuilder response = new StringBuilder();
          String line;
          while ((line = in.readLine()) != null) {
            response.append(line);
          }
          in.close();

          saveToFile(response.toString(), "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\translated_text.txt");

          SwingUtilities.invokeLater(() -> responseTextArea.setText(response.toString()));
        } else {
          SwingUtilities.invokeLater(() -> responseTextArea.setText("Error: " + responseCode));
        }
      } catch (IOException e) {
        SwingUtilities.invokeLater(() -> responseTextArea.setText("Exception: " + e.getMessage()));
      }
    }).start();
  }

  private void textToSpeechButtonClicked() {
    String text = inputTextField.getText();
    // String preprocessedText = preprocessText(text);
    new Thread(() -> {
      try {
        @SuppressWarnings("deprecation")
        URL url = new URL("http://127.0.0.1:5000/convert_to_speech"); // Change the URL to the translation endpoint
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = "{\"text\": \"" + text + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
          byte[] input = jsonInputString.getBytes("utf-8");
          os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
          InputStream inputStream = connection.getInputStream();
          javax.sound.sampled.AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          StringBuilder response = new StringBuilder();
          String line;
          while ((line = in.readLine()) != null) {
            response.append(line);
          }
          in.close();

          saveToFile(response.toString(), "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\translated_text.txt");

          // play the audio
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();

          // clean up resources
          inputStream.close();
          audioInputStream.close();

        } else {
          SwingUtilities.invokeLater(() -> responseTextArea.setText("Error: " + responseCode));
        }
      } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
        SwingUtilities.invokeLater(() -> responseTextArea.setText("Exception: " + e.getMessage()));
      }
    }).start();
  }

  // private void extractTextButtonClicked(File imageFile) {
  // new Thread(() -> {
  // try {
  // @SuppressWarnings("deprecation")
  // URL url = new URL("http://127.0.0.1:5000/extract_text");
  // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  // connection.setRequestMethod("POST");
  // String boundary = "===" + System.currentTimeMillis() + "===";
  // connection.setRequestProperty("Content-Disposition",
  // "form-data; name=\"image\"; filename=\"" + imageFile.getName() + "\"");
  // // connection.setRequestProperty("Content-Type", "multipart/form-data;
  // boundary=" + boundary);
  // connection.setDoOutput(true);

  // // Read the image file as bytes
  // byte[] imageData = Files.readAllBytes(imageFile.toPath());

  // try (OutputStream os = connection.getOutputStream()) {
  // os.write(imageData);
  // os.flush();
  // }

  // int responseCode = connection.getResponseCode();

  // if (responseCode == HttpURLConnection.HTTP_OK) {
  // BufferedReader in = new BufferedReader(new
  // InputStreamReader(connection.getInputStream()));
  // StringBuilder response = new StringBuilder();
  // String line;
  // while ((line = in.readLine()) != null) {
  // response.append(line);
  // }
  // in.close();

  // responseTextArea.setText(response.toString());
  // } else {
  // responseTextArea.setText("Error: " + responseCode);
  // }
  // } catch (IOException e) {
  // responseTextArea.setText("Exception " + e.getMessage());
  // }
  // }).start();
  // }

  private void extractTextButtonClicked(File imageFile) {

    String serverUrl = "http://127.0.0.1:5000/extract_text"; // Change this to your Flask server URL
    // String filePath = "./Dashboard.png"; // Change this to the path of your image
    // file

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
      } else {
        System.out.println("POST request failed with response code " + responseCode);
      }
      connection.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void saveToFile(String text, String fileName) {
    try {
      FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8);
      writer.write(text);
      writer.close();
      System.out.println("File Saved");

      File file = new File(fileName);
      Desktop.getDesktop().open(file);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error saving file." + e.getMessage());
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      SummarizationTranslationGUI gui = new SummarizationTranslationGUI();
      gui.setVisible(true);
    });
  }

}
