# 1. Project Introduction 

[Project Description](Project%20Description.md)

[Demo Link](http://169.60.94.50:8080/BC/)

# 2. How to run: Bayes Cloud Web and REST Server

## REQUIRED SW
1. Eclipse: Eclipse Java EE IDE for Web Developers. Version: Oxygen.2 Release (4.7.2) or higher
2. Apache Tomcat: Version 9.0 or higher
3. MongoDB: Version  3.4.3 or higher
	

## MAIN JAR LIBRARIES
The BC project contains several external Jar files. We list the main jar files which were exported from each open source project and included into the BC project.
	
1. UnBBayes.jar: https://sourceforge.net/p/unbbayes/code/HEAD/tree/trunk/UnBBayes/
2. ModelerLib.jar:  https://github.com/pcyoung75/GMU_HMLP
3. MEBN_RM.jar: https://github.com/pcyoung75/GMU_HMLP
4. MEBN_LN.jar: https://github.com/pcyoung75/GMU_HMLP
5. cps2.jar: https://sourceforge.net/p/prognos/code/HEAD/tree/trunk/

## EXECUTION: Web and Rest Server
- In Eclipse, use "Run on Server".

## POSSIBLE ERROR & SOLUTION
1. The BC project may require "XDoclet". In such case, download "xdoclet-1.2.3.jar" and connect the folder of xdoclet to the BC project.

 
# 3. How to run: Use Case of Situation Awareness for Ebola Virus Disease on Jupyter Notebook
## Required Python Modules
1. Anaconda: Version  4.4.10 or higher
2. folium: use "conda install -c conda-forge folium"

## EXECUTION: Situation Awareness for Ebola Virus Disease
1. Go to the folder "Open-Source\BC\src\test\python" using cmd
2. Run Jupyter Notebook on the folder
3. Open the notebook file "BC_Predictive Situation Awareness for Ebola_OverTime.ipynb"
