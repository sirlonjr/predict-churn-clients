from flask import Flask, request
import pandas as pd
from sklearn.compose import make_column_transformer
from sklearn.preprocessing import OneHotEncoder
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from flask import jsonify
import pickle
app = Flask(__name__)


# Importando os dados e excluindo a coluna id_cliente
dados = pd.read_csv('https://raw.githubusercontent.com/mcalsing/predict-churn-clients/main/churn.csv')
dados = dados.drop('id_cliente', axis=1)


# Separação da base de dados e da coluna churn, armazenando em uma variável x e a variável alvo em y.
x = dados.drop('churn', axis=1)
y = dados['churn']


# Tranformação das variáveis categóricas
one_hot = make_column_transformer((
    OneHotEncoder(drop='if_binary'),
    ['sexo_biologico', 'pais', 'tem_cartao_credito', 'membro_ativo']
),  remainder='passthrough',
    sparse_threshold=0)
colunas = x.columns
x = one_hot.fit_transform(x)
one_hot.get_feature_names_out(colunas)


# Transformação da variável alvo
label_ecoder = LabelEncoder()
y = label_ecoder.fit_transform(y)


# Divisão da base de dados entre treino e teste
x_treino, x_teste, y_treino, y_teste = train_test_split(x, y, stratify=y, random_state=5)


# Treinando o modelo o algoritmo de machine learning
arvore = DecisionTreeClassifier(max_depth=8, random_state=5)
arvore.fit(x_treino, y_treino)


# Testando o modelo
# print(f'Acurácia de treino: {round(arvore.score(x_treino, y_treino)*100, 2)}%')
# print(f'Acurácia de teste: {round(arvore.score(x_teste, y_teste)*100, 2)}%')


# Exportando o modelo
with open('modelo_onehot.pkl', 'wb') as arquivo:
    pickle.dump(one_hot, arquivo)

with open('modelo_arvore.pkl', 'wb') as arquivo:
    pickle.dump(arvore, arquivo)


@app.route("/predict", methods=["POST"])
def predict():

    # Obtém os dados de entrada da solicitação
    data = request.get_json()
    print(data)
    return 'dadooos'
""" 
    # Converte os dados de entrada em um array numpy
    data_frame = pd.DataFrame(data)

    # Converte o array numpy em uma lista
    # novo_dado_list = novo_dado_array.tolist()

    modelo_one_hot = pd.read_pickle('/media/mcalsing/Dados/MeusProjetos/entra21ML/flaskserver/modelo_onehot.pkl')
    modelo_arvore = pd.read_pickle('/media/mcalsing/Dados/MeusProjetos/entra21ML/flaskserver/modelo_arvore.pkl')

    # Realiza a previsão
    novo_dado_transformado = modelo_one_hot.transform(data_frame)
    previsao = modelo_arvore.predict(novo_dado_transformado)
    print('previsao do modelo =', previsao)

    if (previsao == [0]): return 'O modelo previu a não evasão do cliente'
    else: return 'O modelo previu a evasão do cliente'
 """
    # return jsonify({"prediction": str(previsão[0])})


@app.route("/hello")
def hello():
    return "Hello, World!"


if __name__ == "__main__":
    app.run(debug=True)
