# Model developing stage

This directory contains all the requires tools, scripts, packages, ect in order to build the object detection model.
So the model we build basically is an pretrained model that provide by [Tensorflow 2 detection model zoo](https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/tf2_detection_zoo.md) and to do transer learning we need to use a framework which called [TF object detection API](https://github.com/tensorflow/models/blob/master/research/object_detection/README.md). Its had all the tool we need from the object-detection library until di script that could help us to extracting the graph model then create the tflite version of the model. To get your model work to please follow this step below.


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



<div style="text-align: justify">Meet VISION, a comprehensive Reporting Android App for helping the validation process using object detection to provide safety, time efficiency, and quality delivers right at the fingertips of its users. The outline of this project is to create a comprehensive report from users to the government. Our application feature can validate pothole images from users sent to our cloud server using machine learning and the user's report contains location & images to see the details of the roads from other users. Also, we will provide the visualization for the reported case in potholes type, count, dates, and location.</div>


<!-- installation -->

### Anaconda (Optional)

Firstly you need to download anaconda from this [link](
