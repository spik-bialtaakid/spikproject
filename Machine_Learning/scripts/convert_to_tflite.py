import numpy as np
import tensorflow as tf
import os

def representative_dataset():
    for _ in range(100):
      data = np.random.rand(1, 320, 320, 3)
      yield [data.astype(np.float32)]

models = input("the dir contain .pb model :")
models = os.path.join(models)

label_path = input(" the absolute path to your label.pbtxt")
label_path = os.path.join(label_map_path)

output_pat = input(" the the dir where you want it saved")
output_pat = os.path.join(output_pat)

tflite_no_metadata = input(" the absolute path to your output.tflite")
tflite_no_metadata = os.path.join(tflite_no_metadata)

tflite_no_metadata = input(" the absolute path to your output.tflite")
tflite_no_metadata = os.path.join(tflite_no_metadata)


converter = tf.lite.TFLiteConverter.from_saved_model(models)
converter.allow_custom_ops = True
converter.optimizations = [tf.lite.Optimize.DEFAULT]
converter.representative_dataset = representative_dataset
converter.inference_input_type = tf.uint8  # or tf.uint8
converter.inference_output_type = tf.uint8  # or tf.uint8
tflite_quant_model = converter.convert()


with tf.io.gfile.GFile(tflite_no_metadata, 'wb') as f:
  f.write(tflite_quant_model)



from tflite_support.metadata_writers import object_detector
from tflite_support.metadata_writers import writer_utils
from tflite_support import metadata

ObjectDetectorWriter = object_detector.MetadataWriter
model_path = tf_lite_model_path_without_metadata



writer = ObjectDetectorWriter.create_for_inference(
    writer_utils.load_file(model_path), [127.5], [127.5], [label_path])
writer_utils.save_file(writer.populate(), output_pat)

# Verify the populated metadata and associated files.
displayer = metadata.MetadataDisplayer.with_model_file(output_pat)
print("Metadata populated:")
print(displayer.get_metadata_json())
print("Associated file(s) populated:")
print(displayer.get_packed_associated_file_list())
