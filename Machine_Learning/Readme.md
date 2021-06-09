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

first thing you need to Set up the path tree like this.

```sh
TensorFlow/
└─ models/
   ├─ community/
   ├─ official/
   ├─ orbit/
   ├─ research/
   └── ...
```
