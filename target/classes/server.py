#from transformers import (
#    AutoTokenizer,
#    AutoModelForSeq2SeqLM,
#    MBartForConditionalGeneration,
#    MBart50TokenizerFast,
#    AutoModelForMaskedLM,
#    AutoProcessor,
#    AutoModelForCTC,
#)
#
#from flask import Flask, request, jsonify, send_file
#from gtts import gTTS
#from io import BytesIO
#import os
#import subprocess
#import pytesseract
#from PIL import Image
#import io
#from fastapi import FastAPI, UploadFile, File
#
#app = Flask(__name__)
#app.config["MAX_CONTENT_LENGTH"] = 100 * 1024 * 1024  # Limit to 100 MB
#
## load the summarization model
## tokenizer = BartTokenizer.from_pretrained("facebook/bart-large-cnn")
## model = BartForConditionalGeneration.from_pretrained("facebook/bart-large-cnn")
#
## Load summarization model directly
#
#tokenizer = AutoTokenizer.from_pretrained("mrm8488/t5-base-finetuned-summarize-news")
#model = AutoModelForSeq2SeqLM.from_pretrained(
#    "mrm8488/t5-base-finetuned-summarize-news"
#)
#
## tokenizer = AutoTokenizer.from_pretrained(
##     "BeenaSamuel/t5_cnn_daily_mail_abstractive_summarizer_v2"
## )
## model = AutoModelForSeq2SeqLM.from_pretrained(
##     "BeenaSamuel/t5_cnn_daily_mail_abstractive_summarizer_v2"
## )
#
## load the translator model
#
#model_translation = MBartForConditionalGeneration.from_pretrained(
#    "Varosa/mbart-many-to-many"
#)
#tokenizer_translation = MBart50TokenizerFast.from_pretrained(
#    "Varosa/mbart-many-to-many"
#)
#
## model_translation = MBartForConditionalGeneration.from_pretrained("sanjitaa/mbart-many-to-many")
## tokenizer_translation = MBart50TokenizerFast.from_pretrained("sanjitaa/mbart-many-to-many")
#
## load the text_to_speech model
## processor = AutoProcessor.from_pretrained("sumedh/wav2vec2-large-xlsr-marathi")
## model_speech = AutoModelForCTC.from_pretrained("sumedh/wav2vec2-large-xlsr-marathi")
#
#TEXT = ""
#
#
#@app.route("/summarize", methods=["POST"])
#def summarize_text():
#    print("hi")
#    global TEXT
##    data = request.json
#    # text = data["text"]
#    text = TEXT
#    inputs = tokenizer(text, return_tensors="pt", max_length=1024, truncation=True)
#    summary_ids = model.generate(
#        inputs["input_ids"],
#        num_beams=10,
#        min_length=30,
#        max_length=150,
#        early_stopping=True,
#    )
#    summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
#    print(TEXT)
#    return jsonify({"summary": summary})
#
#
## for the varosa model (best one yet)
#@app.route("/translate", methods=["POST"])
#def translate_text():
#    print("hi")
#    
#    global TEXT
##    data = request.json
#    text = TEXT
#    # text = data["text"]
#    tokenizer_translation.src_lang = "en_XX"
#    tokenizer_translation.tgt_lang = "mr_IN"
#    encoded_text = tokenizer_translation(text, return_tensors="pt")
#    print("hi2")
#    generated_tokens = model_translation.generate(
#        **encoded_text,
#        forced_bos_token_id=tokenizer_translation.lang_code_to_id["mr_IN"]
#    )
#    print("h3")
#    translated_text = tokenizer_translation.batch_decode(
#        generated_tokens, skip_special_tokens=True
#    )[0]
#    print("h4")
##    print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))
#    return translated_text.replace(" \u094D ", "\u094D")
#
#
#def translate_text_for_combined(summary):  # only for the summarize_and_translate button
##    data = request.json
#    summary = TEXT
#    # summary = data["text"]
#    tokenizer_translation.src_lang = "en_XX"
#    tokenizer_translation.tgt_lang = "mr_IN"
#    encoded_text = tokenizer_translation(summary, return_tensors="pt")
#    generated_tokens = model_translation.generate(
#        **encoded_text,
#        forced_bos_token_id=tokenizer_translation.lang_code_to_id["mr_IN"]
#    )
#    translated_text = tokenizer_translation.batch_decode(
#        generated_tokens, skip_special_tokens=True
#    )[0]
#    print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))
#
#    return translated_text
#
#
#def summarize_text_for_combined(summ):  # only for the summarize_and_translate button
##    data = request.json
#    summ = TEXT
#    # summ = data["text"]
#    inputs = tokenizer(summ, return_tensors="pt", max_length=1024, truncation=True)
#    summary_ids = model.generate(
#        inputs["input_ids"],
#        num_beams=4,
#        min_length=30,
#        max_length=150,
#        early_stopping=True,
#    )
#    summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
#
#    return jsonify({"summary": summary})
#
#
#@app.route("/summarize_and_translate", methods=["POST"])
#def summarize_and_translate():
##    data = request.json
#    text = TEXT
#    # text = data["text"]
#
#    # startTime = time.time()
#
#    # Summarize the text
#    summary = summarize_text_for_combined(text)
#
#    # time after summarization
#    # summTime = time.time()
#
#    # Translate the summary
#    translated_summary = translate_text_for_combined(summary)
#
#    # time after translation
#    # transTime = time.time()
#
#    # print("time taken by summarizer: ", summTime - startTime)
#    # print("time taken by translator: ", transTime - summTime)
#
#    # Return the translated summary
#    return translated_summary.replace(" \u094D ", "\u094D")
#
#
#@app.route("/convert_to_speech", methods=["POST"])
#def convert_to_speech():
##    data = request.json
#    # text = data["text"]
#    text = TEXT
#    speed = 5
#    duration = 1.5
#
#    summary = summarize_text_for_combined(text)
#
#    translated_summary = translate_text_for_combined(summary)
#
#    tts = gTTS(text=translated_summary, lang="mr", slow=False)
#    tts.speed = speed
#    tts.duration = duration
#    file_path = os.path.join("C:\\Users\\areta\\OneDrive\\Desktop\\delte", "output.mp3")
#    tts.save(file_path)
#    subprocess.run(["start", file_path], shell=True)
#
#    speech_file = BytesIO()
#    tts.write_to_fp(speech_file)
#    speech_file.seek(0)
#
#    # return send_file(speech_file, mimetype="audio/mp3", as_attachment=False)
#    return jsonify({"file_path": file_path})
#
#
#@app.route("/extract_text", methods=["POST"])
#def extractText(image: UploadFile = File(...)):
#    # contents = image.read()
#    # with Image.open(io.BytesIO(contents)).convert("L") as img:
#    #     extracted_text = pytesseract.image_to_string(img)
#
#    # return jsonify({"extracted_text": extracted_text})
#
#    # if "image" not in request.files:
#    #     return jsonify({"error": "no file path"})
#    global TEXT
#
#    if request.method == "POST":
#
#        image = extracted_method()
#        if image.filename == "":
#            return jsonify({"error": "no selected file"})
#
#        contents = image.read()
#        with Image.open(io.BytesIO(contents)).convert("L") as img:
#            extracted_text = pytesseract.image_to_string(img)
#
#        text_file_path = (
#            "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt"
#        )
#        with open(text_file_path, "w", encoding="utf-8") as text_file:
#            text_file.write(extracted_text)
#
#        os.startfile(text_file_path)
#
#        TEXT = extracted_text
#
#        return jsonify({"extracted_text": extracted_text})
#
#def extracted_method():
#    return request.files["file"]
#
#    # if request.method == "POST":
#    #     file = request.files["file"]
#    #     if file:
#    #         filename = file.filename
#    #         file.save(os.path.join("./", "Awoo.jpg"))
#    #         return {"message": "File uploaded successfully", "filename": filename}
#
#
## @app.route("/summary")
## def index():
##     return "Hello, World!"
#
#
#if __name__ == "__main__":
#    app.run(debug=True)
#
#
## @app.route("/translate", methods=["POST"])
## def translate_text():
##     data = request.json
##     text = data["text"]
#
##     inputs_translation = tokenizer_translation(
##         "translate English to Marathi: " + text, return_tensors="pt"
##     )
##     translation_ids = model_translation.generate(
##         inputs_translation["input_ids"],
##         max_length=100,
##         num_beams=4,
##         early_stopping=True,
##     )
##     translated_text = tokenizer_translation.decode(
##         translation_ids[0], skip_special_tokens=True
##     )
#
##     return jsonify({"translation": translated_text})

from transformers import (
    AutoTokenizer,
    AutoModelForSeq2SeqLM,
    MBartForConditionalGeneration,
    MBart50TokenizerFast,
    AutoModelForMaskedLM,
    AutoProcessor,
    AutoModelForCTC,
)

from flask import Flask, request, jsonify, send_file
from gtts import gTTS
from io import BytesIO
import os
import subprocess
import pytesseract
from PIL import Image
import io
from fastapi import FastAPI, UploadFile, File

app = Flask(__name__)

app.config["MAX_CONTENT_LENGTH"] = 100 * 1024 * 1024  # Limit to 100 MB

# load the summarization model
# tokenizer = BartTokenizer.from_pretrained("facebook/bart-large-cnn")
# model = BartForConditionalGeneration.from_pretrained("facebook/bart-large-cnn")

# Load translation model directly
model_translation = MBartForConditionalGeneration.from_pretrained("Varosa/mbart-many-to-many")
tokenizer_translation = MBart50TokenizerFast.from_pretrained("Varosa/mbart-many-to-many")

tokenizer = AutoTokenizer.from_pretrained("mrm8488/t5-base-finetuned-summarize-news")

model = None
try:
    model = AutoModelForSeq2SeqLM.from_pretrained(
        "mrm8488/t5-base-finetuned-summarize-news"
    )
    print("No error")
except: 
    print("Error")


try:
    model_translation = MBartForConditionalGeneration.from_pretrained(
        "Varosa/mbart-many-to-many"
    )
    print("No translation error")
except:
    print("Translation model error")
tokenizer_translation = MBart50TokenizerFast.from_pretrained(
    "Varosa/mbart-many-to-many"
)

TEXT = ""


@app.route("/summarize", methods=["POST"])
def summarize_text():
    print("hi")
    global TEXT
    # data = request.json
    # text = data["text"]
    text = TEXT
    inputs = tokenizer(text, return_tensors="pt", max_length=1024, truncation=True)
    summary_ids = model.generate(
        inputs["input_ids"],
        num_beams=10,
        min_length=30,
        max_length=150,
        early_stopping=True,
    )
    summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
    print(TEXT)
    return jsonify({"summary": summary})


# for the varosa model (best one yet)
@app.route("/translate", methods=["POST"])
def translate_text():
    global TEXT
    # data = request.json
    text = TEXT
    # text = data["text"]
    tokenizer_translation.src_lang = "en_XX"
    tokenizer_translation.tgt_lang = "mr_IN"
    encoded_text = tokenizer_translation(text, return_tensors="pt")
    print("hi2")
    generated_tokens = model_translation.generate(
        **encoded_text,
        forced_bos_token_id=tokenizer_translation.lang_code_to_id["mr_IN"]
    )
    print("h3")
    translated_text = tokenizer_translation.batch_decode(
        generated_tokens, skip_special_tokens=True
    )[0]
    print("h4")
    # print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))
    return translated_text.replace(" \u094D ", "\u094D")


def translate_text_for_combined(summary):  # only for the summarize_and_translate button
    # data = request.json
    global TEXT 
    summary = TEXT
    # summary = data["text"]
    tokenizer_translation.src_lang = "en_XX"
    tokenizer_translation.tgt_lang = "mr_IN"
    encoded_text = tokenizer_translation(summary, return_tensors="pt")
    generated_tokens = model_translation.generate(
        **encoded_text,
        forced_bos_token_id=tokenizer_translation.lang_code_to_id["mr_IN"]
    )
    translated_text = tokenizer_translation.batch_decode(
        generated_tokens, skip_special_tokens=True
    )[0]
#    print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))

    return translated_text


def summarize_text_for_combined(summ):  # only for the summarize_and_translate button
    # data = request.json
    global TEXT
    summ = TEXT
    # summ = data["text"]
    inputs = tokenizer(summ, return_tensors="pt", max_length=1024, truncation=True)
    summary_ids = model.generate(
        inputs["input_ids"],
        num_beams=4,
        min_length=30,
        max_length=150,
        early_stopping=True,
    )
    summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)

    return jsonify({"summary": summary})


@app.route("/summarize_and_translate", methods=["POST"])
def summarize_and_translate():
    # data = request.json
    global TEXT
    
    text = TEXT
    # text = data["text"]

    # Summarize the text
    summary = summarize_text_for_combined(text)

    # Translate the summary
    translated_summary = translate_text_for_combined(summary)
    
    # Return the translated summary
    return translated_summary.replace(" \u094D ", "\u094D")


def translate_text_for_speech(t):
    global TEXT
    # data = request.json
    text = TEXT
    # text = data["text"]
    tokenizer_translation.src_lang = "en_XX"
    tokenizer_translation.tgt_lang = "mr_IN"
    encoded_text = tokenizer_translation(text, return_tensors="pt")
    print("hi2")
    generated_tokens = model_translation.generate(
        **encoded_text,
        forced_bos_token_id=tokenizer_translation.lang_code_to_id["mr_IN"]
    )
    print("h3")
    translated_text = tokenizer_translation.batch_decode(
        generated_tokens, skip_special_tokens=True
    )[0]
    print("h4")
    # print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))
    return translated_text.replace(" \u094D ", "\u094D")


# only for summarize and translate combined button for now
@app.route("/convert_to_speech", methods=["POST"])
def convert_to_speech():
    # data = request.json
    # text = data["text"]
    text = TEXT
    speed = 5
    duration = 1.5

    translated_summary = translate_text_for_speech(text)

    tts = gTTS(text=translated_summary, lang="mr", slow=False)
    tts.speed = speed
    tts.duration = duration
    try:
        file_path = os.path.join(
            "C:\\Users\\areta\\OneDrive\\Desktop\\delte", "output.mp3"
        )
        tts.save(file_path)
        subprocess.run(["start", file_path], shell=True)
        return jsonify({"file_path": file_path})

    except Exception as e:
        return jsonify({"file_path": file_path})


@app.route("/extract_text", methods=["POST"])
def extractText(image: UploadFile = File(...)):
    # contents = image.read()
    # with Image.open(io.BytesIO(contents)).convert("L") as img:
    #     extracted_text = pytesseract.image_to_string(img)

    # return jsonify({"extracted_text": extracted_text})

    # if "image" not in request.files:
    #     return jsonify({"error": "no file path"})
    global TEXT

    if request.method == "POST":

        image = extracted_method()
        if image.filename == "":
            return jsonify({"error": "no selected file"})

        contents = image.read()
        with Image.open(io.BytesIO(contents)).convert("L") as img:
            extracted_text = pytesseract.image_to_string(img)

        text_file_path = (
            "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt"
        )
        with open(text_file_path, "w", encoding="utf-8") as text_file:
            text_file.write(extracted_text)

#        os.startfile(text_file_path)

        TEXT = extracted_text

        return jsonify({"extracted_text": extracted_text})


def extracted_method():
    return request.files["file"]

if __name__ == "__main__":
    app.run(debug=False, port=8080)

