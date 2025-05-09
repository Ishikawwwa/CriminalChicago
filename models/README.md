# Crime Prediction Model - README  

## Overview  
This project focuses on predicting the time until the next crime occurs in the same district using machine learning. Given the complexity of the task, I implemented several regression models and incorporated temporal features to improve performance.  

## Data Preprocessing  
The raw dataset contained crime records with multiple features. I performed the following preprocessing steps:  
- Dropped rows with missing values.  
- Excluded features that were unlikely to contribute meaningfully to the model.  
- Encoded categorical variables.  
- Engineered a new target feature called `label`, representing the time (in seconds) until the next crime in the same district. This was calculated as the time difference between consecutive crimes in each district.  

Since the models used are relatively simple, I kept only the most relevant features and dropped those that were either useless or not significantly useful.  

### Feature Engineering  
To enhance the model's predictive power, I incorporated information from the **three most recent crimes** in the same district before each event. For each record, I added selected features from these past crimes (e.g., `primary_type_enc_prev1`, `primary_type_enc_prev2`, `primary_type_enc_prev3`), allowing the model to capture short-term trends and patterns. This approach improved performance compared to using only current crime features.  

Data looks like this aftet these steps:
![alt text](image.png)

## Model Training   
Given the complexity of the task, more advanced models (deep neural networks, time series models, etc.) would likely perform better. However, due to resource constraints, I focused on simpler models and used **GridSearchCV** for hyperparameter tuning. The following models were trained and evaluated:  
- **Linear Regression**  
- **Random Forest Regressor**  
- **Support Vector Regressor (SVR)**  
- **LightGBM Regressor (LGBM)**  

I experimented with different encoding methods and even tested training without encoding to compare results.  

## Evaluation  
After tuning hyperparameters, I evaluated the models on a held-out test set (20% of the data). Despite the simplicity of the models, they performed reasonably well. The best-performing model achieved the following results:  

![alt text](image-1.png)



## Conclusion  
By incorporating historical crime data from the same district, I improved the model's ability to predict crime occurrence times. While more complex models could yield better results, the current implementation provides a strong baseline for future enhancements.  

Unfortunately I wasn't able to run some of my models on the server, due to the lack of resources, so I had the only chance to work on google colab.

### Next Steps  
- Experiment with more advanced models (e.g., LSTMs, Transformer-based models).  
- Optimize feature selection further to reduce noise.  
