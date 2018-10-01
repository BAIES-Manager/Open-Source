# 1. Project Introduction 

[Project Description](Project%20Description.md)

[Demo Link](http://169.60.94.50:8080/BC/)

### Bayes Cloud
[![Alt text](https://img.youtube.com/vi/bvYNIkWFsSE/0.jpg)](https://www.youtube.com/watch?v=bvYNIkWFsSE)

### Holistic Situational Awareness Using Bayesian Network Integration

A Bayesian Network (BN) is a probabilistic graphical model that represents a joint distribution on a set of random variables in a compact form that exploits conditional independence relationships. BN is a powerful tool for predictive situation awareness, characterized by understanding the current situations and predicting the future situations. It also fosters making decisions and addressing problems under uncertainty. Subject matter experts (SME), as collective intelligence, develop a causal BN model and often integrate multi-BN models into one joint causal model to obtain a third model. 

BAIES, LLC (the team) has developed a computing system platform based on a Collective Intelligence Multi-Model Integration. Bayes Cloud Service (the platform), which utilized IBM Cloud Services, to provide three main functions for SME: (1) collaborative causal modeling, (2) causal model integration, and (3) causal model reasoning. Modeling allows users to build a BN model or modify existing BN models using a Bayesian network script code. The Bayesian network script is an intuitive and user-friendly language to let users easily write codes to design BN models and apply machine learning. Without loss of generality, model integration can be viewed as a process for merging two BN models to obtain a third BN model. The resulting model shall embody the information from the original models and support joint reasoning unattainable solely by the individual models. While this integration process can be performed manually, the platform also provides automatic model integration function using non-linear optimization methods, given common random variables shared by models to be integrated. Model reasoning is an application in the platform which provides an interface for users to update various reasoning information from new evidence to change the model in real-time and as needed. Users can see the instant reasoning result from the BN model based on given data. 

In the platform, the model along with datasets used to develop the model can be registered and saved in the cloud. Later, the model can be searched, retrieved, and shared by other users for further editing and integration with other BN models in the cloud. The team expects the number of BN models in the platform will grow exponentially as more users contribute to developing BN models using auto-model integration function. 

While BN has been applied in a wide range of domains, the team used the platform to develop a sample BN model in the medical field, especially focusing on predictive situation awareness for infectious disease such as Ebola Virus Disease (EVD).  Various types of EVD epidemiological analysis have been researched independently to understand characteristics of EVD, predict dynamics, and identify optimal control strategies to decelerate the EVD spread, including Geospatial Dangerousness Model, Regional Virus Spread Model, Virus Mutation Model, and Infectious Disease Patient Model. The team developed a BN model for each analysis and performed model integration to produce an advanced integrated EVD BN model. Using the integrated model, holistic situational awareness regarding the Ebola epidemic is accomplished to support decision makers in choosing a proper treatment among all possible options.


# 2. How to run: Bayes Cloud Web and REST Server
![Bayes Cloud](doc/img/BC%20v2.PNG)

## Required SW
1. Eclipse: Eclipse Java EE IDE for Web Developers. Version: Oxygen.2 Release (4.7.2) or higher
2. Apache Tomcat: Version 9.0 or higher
3. MongoDB: Version  3.4.3 or higher

## Main Jar Libraries
The BC project contains several external Jar files. We list the main jar files which were exported from each open source project and included into the BC project.
	
1. UnBBayes.jar: https://sourceforge.net/p/unbbayes/code/HEAD/tree/trunk/UnBBayes/
2. ModelerLib.jar:  https://github.com/pcyoung75/GMU_HMLP
3. MEBN_RM.jar: https://github.com/pcyoung75/GMU_HMLP
4. MEBN_LN.jar: https://github.com/pcyoung75/GMU_HMLP
5. cps2.jar: https://sourceforge.net/p/prognos/code/HEAD/tree/trunk/

## Execution: Web and Rest Server
- Use "Run on Server" on Eclipse

## Possible Error & Solution 
1. The BC project may require "XDoclet". In such case, download "xdoclet-1.2.3.jar" and connect the folder of xdoclet to the BC project.


# 3. How to run: Use Case of Situation Awareness for Ebola Virus Disease on Jupyter Notebook
![Situation Awareness for Ebola Virus Disease](doc/img/Situation%20Awareness%20for%20Ebola%20Virus%20Disease.PNG)

## Required Python Modules
1. Anaconda: Version  4.4.10 or higher
2. folium: use "conda install -c conda-forge folium" using cmd

## Execution: Situation Awareness for Ebola Virus Disease
1. Go to the folder "Open-Source\BC\src\test\python" using cmd
2. Run Jupyter Notebook on the folder
3. Open the notebook file "BC_Predictive Situation Awareness for Ebola_OverTime.ipynb"


# 4. User Guide Videos

1 Model Editing
[![Alt text](https://img.youtube.com/vi/Ylk_f-nnlZ0/0.jpg)](https://www.youtube.com/watch?v=Ylk_f-nnlZ0)

2 Model Searching
[![Alt text](https://img.youtube.com/vi/Dd3vVU2ptKs/0.jpg)](https://www.youtube.com/watch?v=Dd3vVU2ptKs)

3 Model Integrating
[![Alt text](https://img.youtube.com/vi/O0OqJVyeAkw/0.jpg)](https://www.youtube.com/watch?v=O0OqJVyeAkw)

4 Model Registering
[![Alt text](https://img.youtube.com/vi/FT1eEgKl8Ac/0.jpg)](https://www.youtube.com/watch?v=FT1eEgKl8Ac)

4 Model Registering
[![Alt text](https://img.youtube.com/vi/FT1eEgKl8Ac/0.jpg)](https://www.youtube.com/watch?v=FT1eEgKl8Ac)

5 Machine Learning
[![Alt text](https://img.youtube.com/vi/hF-ZeEF3m7o/0.jpg)](https://www.youtube.com/watch?v=hF-ZeEF3m7o)

