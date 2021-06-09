# Model developing stage

This directory contains all the requires tools, scripts, packages, ect in order to build the object detection model.
So the model we build basically is an pretrained model that provide by [Tensorflow 2 detection model zoo](https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/tf2_detection_zoo.md) and to do transer learning we need to use a framework which called [TF object detection API](https://github.com/tensorflow/models/blob/master/research/object_detection/README.md). Its had all the tool we need from the object-detection library until di script that could help us to extracting the graph model then create the tflite version of the model. To get your model work to please follow this step below. For the furthermore information about how to use the TF objet detection API you can refer to this [page](https://tensorflow-object-detection-api-tutorial.readthedocs.io/en/latest/).


<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#inst">installation</a>
      <ul>
        <li><a href="#ana">Anaconda (optional)</a></li>
        <li><a href="#tf">Tensorflow</a></li>
        <li><a href="#gpu">GPU Support (Optional)</a></li>
        <li><a href="#obj">Object detection</a></li>
        <li><a href="#test">Testing</a></li>
      </ul>
    </li>
    <li>
      <a href="#set">Dataset</a>
      <ul>
        <li><a href="#preproc">Preprocessing</a></li>
        <li><a href="#install">Labelling</a></li>
        <li><a href="#tfrec">Create TF record</a></li>
      </ul>
    </li>
    <li>
      <a href="#train">Training</a>
      <ul>
        <li><a href="#prerequisites">Set up model</a></li>
        <li><a href="#installation">Pipeline config</a></li>
        <li><a href="#installation">start training</a></li>
      </ul>
    </li>
  </ol>
</details>



## installation

TF object detection API provide two type of Pipeline, which are the tf1 and tf2. The differences is the tf2 compatible to use in tensorflow 2.x environment. To get started lets create the virtual environment first.

### Anaconda (Optional)

This step is not requires to do if you one to use another distribution. For the purpose of learning, we consider to use Anaconda for creating virtual environment. To use Anaconda distribution you just need to download and install its first [Windows|Linux](https://www.anaconda.com/products/individual)|

After your anaconda installed you can open conda terminal in Windows or type conda in linux terminal.

Once the terminal opened, try to type this command.

```sh
conda create -n tensor-environ pip python=3.9
```

To activate, just type.
```sh
conda activate tensor-environ
```

Then within tensor-environ you can start installing the tensorflow 2.5.0.
```sh
pip install --ignore-installed --upgrade tensorflow==2.5.0
```

### GPU Support (Optional)
Using a GPU to run TensorFlow is not necessary, but its give better peformance. Therefore, if your machine is equipped with a compatible CUDA-enabled GPU, it is recommended that you follow the steps listed below to install the relevant libraries necessary to enable TensorFlow to make use of your GPU. Firstly download the [CUDA Toolkit 11.2](https://developer.nvidia.com/cuda-11.2.2-download-archive?target_os=Windows&target_arch=x86_64&target_version=10&target_type=exenetwork), Installation instructions can be found [here](https://docs.nvidia.com/cuda/archive/11.2.2/cuda-installation-guide-microsoft-windows/index.html)

### Object detection library
Maybe you can install the object detection from pip its not recommended because there a huge gap beetwen the pip version which the TF object detection API used.

first thing you need to create the root folder. Then download or clone the [TF objet detection API](https://github.com/tensorflow/models/blob/master/research/object_detection/README.md). After that you will have your path tree like this.

```sh
TensorFlow/
└─ models/
   ├─ community/
   ├─ official/
   ├─ orbit/
   ├─ research/
   └── ...
```
Then download the [protoc](https://github.com/protocolbuffers/protobuf/releases) and set up if you haven't yet. Once your protoc ready, run this command from your root path.

```sh
protoc TensorFlow/models/research/object_detection/protos/*.proto --python_out=.
```

Then you need a COCO API and its quite simple to do you just need to run those two command.

```sh
pip install cython
pip install git+https://github.com/philferriere/cocoapi.git#subdirectory=PythonAPI
```

Finally we came to install our object-detection API, lets to this command.

```sh
cp Tensorflow/models/research/object_detection/packages/tf2/setup.py .
python -m pip install --use-feature=2020-resolver .
```

Lets test your object-detection api

```sh
python object_detection/builders/model_builder_tf2_test.py
```

its should return result like This

```sh
...
[       OK ] ModelBuilderTF2Test.test_create_ssd_models_from_config
[ RUN      ] ModelBuilderTF2Test.test_invalid_faster_rcnn_batchnorm_update
INFO:tensorflow:time(__main__.ModelBuilderTF2Test.test_invalid_faster_rcnn_batchnorm_update): 0.0s
I0608 18:49:13.183754 29296 test_util.py:2102] time(__main__.ModelBuilderTF2Test.test_invalid_faster_rcnn_batchnorm_update): 0.0s
[       OK ] ModelBuilderTF2Test.test_invalid_faster_rcnn_batchnorm_update
[ RUN      ] ModelBuilderTF2Test.test_invalid_first_stage_nms_iou_threshold
INFO:tensorflow:time(__main__.ModelBuilderTF2Test.test_invalid_first_stage_nms_iou_threshold): 0.0s
I0608 18:49:13.186750 29296 test_util.py:2102] time(__main__.ModelBuilderTF2Test.test_invalid_first_stage_nms_iou_threshold): 0.0s
[       OK ] ModelBuilderTF2Test.test_invalid_first_stage_nms_iou_threshold
[ RUN      ] ModelBuilderTF2Test.test_invalid_model_config_proto
INFO:tensorflow:time(__main__.ModelBuilderTF2Test.test_invalid_model_config_proto): 0.0s
I0608 18:49:13.188250 29296 test_util.py:2102] time(__main__.ModelBuilderTF2Test.test_invalid_model_config_proto): 0.0s
[       OK ] ModelBuilderTF2Test.test_invalid_model_config_proto
[ RUN      ] ModelBuilderTF2Test.test_invalid_second_stage_batch_size
INFO:tensorflow:time(__main__.ModelBuilderTF2Test.test_invalid_second_stage_batch_size): 0.0s
I0608 18:49:13.190746 29296 test_util.py:2102] time(__main__.ModelBuilderTF2Test.test_invalid_second_stage_batch_size): 0.0s
[       OK ] ModelBuilderTF2Test.test_invalid_second_stage_batch_size
[ RUN      ] ModelBuilderTF2Test.test_session
[  SKIPPED ] ModelBuilderTF2Test.test_session
[ RUN      ] ModelBuilderTF2Test.test_unknown_faster_rcnn_feature_extractor
INFO:tensorflow:time(__main__.ModelBuilderTF2Test.test_unknown_faster_rcnn_feature_extractor): 0.0s
I0608 18:49:13.193742 29296 test_util.py:2102] time(__main__.ModelBuilderTF2Test.test_unknown_faster_rcnn_feature_extractor): 0.0s
[       OK ] ModelBuilderTF2Test.test_unknown_faster_rcnn_feature_extractor
[ RUN      ] ModelBuilderTF2Test.test_unknown_meta_architecture
INFO:tensorflow:time(__main__.ModelBuilderTF2Test.test_unknown_meta_architecture): 0.0s
I0608 18:49:13.195241 29296 test_util.py:2102] time(__main__.ModelBuilderTF2Test.test_unknown_meta_architecture): 0.0s
[       OK ] ModelBuilderTF2Test.test_unknown_meta_architecture
[ RUN      ] ModelBuilderTF2Test.test_unknown_ssd_feature_extractor
INFO:tensorflow:time(__main__.ModelBuilderTF2Test.test_unknown_ssd_feature_extractor): 0.0s
I0608 18:49:13.197239 29296 test_util.py:2102] time(__main__.ModelBuilderTF2Test.test_unknown_ssd_feature_extractor): 0.0s
[       OK ] ModelBuilderTF2Test.test_unknown_ssd_feature_extractor
----------------------------------------------------------------------
Ran 24 tests in 29.980s

OK (skipped=1)
```

## Dataset
You can get our dataset from this [link](https://www.kaggle.com/ridhomuhammad/sibi-language-object-detection), and the tfrecord are [there](https://app.roboflow.com/ds/6LLb0DMJ0O?key=Yl3Nl86oBD). But if you want to know how generate your own dataset you can follow this step below.

### Preprocessing
This directory contain all the script needed to do the preprocessing. So to get your dataset compatible with the ssd mobilnet, firstly you need to create the root path of your dataset. then create the folder that its name refer to your object that being detect (e. g Human -> dataset/Human), the store the image you have corresponding to each object folder. Once ready run this [script](https://github.com/spik-bialtaakid/spikproject/blob/main/Machine_Learning/scripts/reducing_size.py).

```sh
(tensor-env) ..\Machine_Learning\scripts\ > python reducing_size.py -i D:\Dataset\Human -n 3
```

the -i mean where your image located than the -n mean how many time you want it to reduce. Repeate this process to every object folder within your dataset directory. After that you can start using this [script](https://github.com/spik-bialtaakid/spikproject/blob/main/Machine_Learning/scripts/preprocessing.py) to do preprocessing.

In order to avoid any collusion or error please follow this step.

```sh
(tensor-env) ..\Machine_Learning\scripts\ > python preprocessing.py

(tensor-env) ..\Machine_Learning\scripts\ >
root_path :
--------------------
select task above :
1. rename images name based on alphabet dir
2. check the sum of images contain in each alphabet dir
3. equalize the sum of all images set
4. delete alphabet folder an put all into root folder
your selection :

```
So you just need to pass the root folder path, then there 4 things you can do. as mention above, consider to do the step above sequential not arbitrary
