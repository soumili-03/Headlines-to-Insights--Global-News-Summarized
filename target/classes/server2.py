# import wave
# from transformers import (
#     AutoTokenizer,
#     AutoModelForSeq2SeqLM,
#     MBartForConditionalGeneration,
#     MBart50TokenizerFast,
#     AutoModelForMaskedLM,
#     AutoProcessor,
#     AutoModelForCTC,
# )

# from flask import Flask, request, jsonify, send_file
# from gtts import gTTS
# from io import BytesIO
# import os
# import subprocess
# import pytesseract
# from PIL import Image
# import io
# from fastapi import FastAPI, UploadFile, File

# app = Flask(__name__)
# app.config["MAX_CONTENT_LENGTH"] = 100 * 1024 * 1024  # Limit to 10 MB

# tokenizer = AutoTokenizer.from_pretrained(
#     "BeenaSamuel/t5_cnn_daily_mail_abstractive_summarizer_v2"
# )
# model = AutoModelForSeq2SeqLM.from_pretrained(
#     "BeenaSamuel/t5_cnn_daily_mail_abstractive_summarizer_v2"
# )

# # load the translator model

# model_translation = MBartForConditionalGeneration.from_pretrained(
#     "Varosa/mbart-many-to-many"
# )
# tokenizer_translation = MBart50TokenizerFast.from_pretrained(
#     "Varosa/mbart-many-to-many"
# )


# @app.route("/extract_text", methods=["POST"])
# def extractText(image: UploadFile = File(...)):
#     if "image" not in request.files:
#         return jsonify({"error": "no file path"})

#     image = request.files["image"]
#     if image.filename == "":
#         return jsonify({"error": "no selected file"})

#     contents = image.read()
#     with Image.open(io.BytesIO(contents)).convert("L") as img:
#         extracted_text = pytesseract.image_to_string(img)

#     # Save extracted text to a text file
#     text_file_path = "C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt"
#     with open(text_file_path, "w", encoding="utf-8") as text_file:
#         text_file.write(extracted_text)

#     return jsonify({"extracted_text": extracted_text, "text_file_path": text_file_path})


# @app.route("/summarize", methods=["POST"])
# def summarize_text():
#     text_file_path = request.json.get("C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt")
#     with open(text_file_path, "r", encoding="utf-8") as text_file:
#         text = text_file.read()

#     inputs = tokenizer(text, return_tensors="pt", max_length=1024, truncation=True)
#     summary_ids = model.generate(
#         inputs["input_ids"],
#         num_beams=4,
#         min_length=30,
#         max_length=150,
#         early_stopping=True,
#     )
#     summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
#     return jsonify({"summary": summary})


# # for the varosa model (best one yet)
# @app.route("/translate", methods=["POST"])
# def translate_text():
#     text_file_path = request.json.get("C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt")
#     with open(text_file_path, "r", encoding="utf-8") as text_file:
#         text = text_file.read()

#     tokenizer_translation.src_lang = "en_XX"
#     tokenizer_translation.tgt_lang = "mr_IN"
#     encoded_text = tokenizer_translation(text, return_tensors="pt")
#     generated_tokens = model_translation.generate(
#         **encoded_text,
#         forced_bos_token_id=tokenizer_translation.lang_code_to_id["mr_IN"]
#     )
#     translated_text = tokenizer_translation.batch_decode(
#         generated_tokens, skip_special_tokens=True
#     )[0]
#     print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))
#     return translated_text.replace(" \u094D ", "\u094D")


# def translate_text_for_combined(summary):  # only for the summarize_and_translate button
#     text_file_path = request.json.get("C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt")
#     with open(text_file_path, "r", encoding="utf-8") as text_file:
#         text = text_file.read()

#     tokenizer_translation.src_lang = "en_XX"
#     tokenizer_translation.tgt_lang = "mr_IN"
#     encoded_text = tokenizer_translation(summary, return_tensors="pt")
#     generated_tokens = model_translation.generate(
#         **encoded_text,
#         forced_bos_token_id=tokenizer_translation.lang_code_to_id["mr_IN"]
#     )
#     translated_text = tokenizer_translation.batch_decode(
#         generated_tokens, skip_special_tokens=True
#     )[0]
#     print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))

#     return translated_text


# def summarize_text_for_combined(summ):  # only for the summarize_and_translate button
#     text_file_path = request.json.get("C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt")
#     with open(text_file_path, "r", encoding="utf-8") as text_file:
#         text = text_file.read()
#     inputs = tokenizer(summ, return_tensors="pt", max_length=1024, truncation=True)
#     summary_ids = model.generate(
#         inputs["input_ids"],
#         num_beams=4,
#         min_length=30,
#         max_length=150,
#         early_stopping=True,
#     )
#     summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)

#     return jsonify({"summary": summary})


# @app.route("/summarize_and_translate", methods=["POST"])
# def summarize_and_translate():
#     text_file_path = request.json.get("C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt")
#     with open(text_file_path, "r", encoding="utf-8") as text_file:
#         text = text_file.read()

#     summary = summarize_text_for_combined(text)

#     translated_summary = translate_text_for_combined(summary)

#     return translated_summary.replace(" \u094D ", "\u094D")


# @app.route("/convert_to_speech", methods=["POST"])
# def convert_to_speech():
#     text_file_path = request.json.get("C:\\Users\\areta\\OneDrive\\Desktop\\delte\\extracted_text.txt")
#     with open(text_file_path, "r", encoding="utf-8") as text_file:
#         text = text_file.read()

#     speed = 5
#     duration = 1.5

#     summary = summarize_text_for_combined(text)

#     translated_summary = translate_text_for_combined(summary)

#     tts = gTTS(text=translated_summary, lang="mr", slow=False)
#     tts.speed = speed
#     tts.duration = duration
#     file_path = os.path.join("C:\\Users\\areta\\OneDrive\\Desktop\\delte", "output.mp3")
#     tts.save(file_path)
#     subprocess.run(["start", file_path], shell=True)

#     return jsonify({"file_path": file_path})


# if __name__ == "__main__":
#     app.run(debug=True)


import wave
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
app.config["MAX_CONTENT_LENGTH"] = 100 * 1024 * 1024  # Limit to 10 MB

# load the summarization model

tokenizer = AutoTokenizer.from_pretrained(
    "BeenaSamuel/t5_cnn_daily_mail_abstractive_summarizer_v2"
)
model = AutoModelForSeq2SeqLM.from_pretrained(
    "BeenaSamuel/t5_cnn_daily_mail_abstractive_summarizer_v2"
)

# load translation model

model_translation = MBartForConditionalGeneration.from_pretrained(
    "Varosa/mbart-many-to-many"
)
tokenizer_translation = MBart50TokenizerFast.from_pretrained(
    "Varosa/mbart-many-to-many"
)


@app.route("/summarize", methods=["POST"])
def summarize_text():
    data = request.json
    text = data["text"]
    inputs = tokenizer(text, return_tensors="pt", max_length=1024, truncation=True)
    summary_ids = model.generate(
        inputs["input_ids"],
        num_beams=4,
        min_length=30,
        max_length=150,
        early_stopping=True,
    )
    summary = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
    return jsonify({"summary": summary})


# for the varosa model (best one yet)
@app.route("/translate", methods=["POST"])
def translate_text():
    data = request.json
    text = data["text"]
    tokenizer_translation.src_lang = "en_XX"
    tokenizer_translation.tgt_lang = "mr_IN"
    encoded_text = tokenizer_translation(text, return_tensors="pt")
    generated_tokens = model_translation.generate(
        **encoded_text,
        forced_bos_token_id=tokenizer_translation.lang_code_to_id["mr_IN"]
    )
    translated_text = tokenizer_translation.batch_decode(
        generated_tokens, skip_special_tokens=True
    )[0]
    print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))
    return translated_text.replace(" \u094D ", "\u094D")


def translate_text_for_combined(summary):  # only for the summarize_and_translate button
    data = request.json
    summary = data["text"]
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
    print("translated text: ", translated_text.replace(" \u094D ", "\u094D"))

    return translated_text


def summarize_text_for_combined(summ):  # only for the summarize_and_translate button
    data = request.json
    summ = data["text"]

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
    data = request.json
    text = data["text"]

    summary = summarize_text_for_combined(text)

    translated_summary = translate_text_for_combined(summary)

    return translated_summary.replace(" \u094D ", "\u094D")


@app.route("/convert_to_speech", methods=["POST"])
def convert_to_speech():
    data = request.json
    text = data["text"]

    speed = 5
    duration = 1.5

    summary = summarize_text_for_combined(text)

    translated_summary = translate_text_for_combined(summary)

    tts = gTTS(text=translated_summary, lang="mr", slow=False)
    tts.speed = speed
    tts.duration = duration
    file_path = os.path.join("C:\\Users\\areta\\OneDrive\\Desktop\\delte", "output.mp3")
    tts.save(file_path)
    subprocess.run(["start", file_path], shell=True)

    return jsonify({"file_path": file_path})


@app.route("/extract_text", methods=["POST"])
def extractText(image: UploadFile = File(...)):

    if "image" not in request.files:
        return jsonify({"error": "no file part"})

    image = request.files["image"]
    if image.filename == "":
        return jsonify({"error": "no selected file"})

    contents = image.read()
    with Image.open(io.BytesIO(contents)).convert("L") as img:
        extracted_text = pytesseract.image_to_string(img)

    text_file_path = "extracted_text.txt"
    with open(text_file_path, "w", encoding="utf-8") as text_file:
        text_file.write(extracted_text)

    return jsonify({"extracted_text": extracted_text})


if __name__ == "__main__":
    app.run(debug=True)
