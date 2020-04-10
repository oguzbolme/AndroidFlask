from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route("/")
def index():
    
    # Burası anasayfamız
    return "Flask Servisimize Hoş Geldin !"


@app.route('/names/<name>')
def hello_user(name):

    # Bu fonksiyon bir GET metodu. Android tarafında girilen ad ve soyadı burada aşağıdaki formatta androide geri döndürmemize yarıyor.
    return "Merhaba %s, işte flask ile bağlantı böyle kuruluyor. Hadi eyw :)" %name


@app.route('/api/postName', methods=['POST'])
def post_name_surname():
    
    # Bu fonksiyon ise bir POST metodu. Android tarafında girilen ad ve soyadı burada konsola yazdırıyoruz ve geriye başarılı bir şekilde gönderildi mesajını döndürüyoruz.
    json = request.get_json()
    print(json)
    if len(json['name'] and json['surname']) == 0:
        return jsonify({'Hata': 'Hatali girdi tespit edildi !'})

    return jsonify({'Veriler basariyla gonderildi: ': json['name'] + " " + json['surname']})


# Aslında yukarıdaki 2 metodda hem post hem get işlemleri yapıyorlar.

# Bu kısımda host dediğimiz yerin içine kendi ip değerimizi yazmalıyız.
# Aynı şekilde android kısmında API sınıfının içindeki baseUrl fonksiyonumuzun içerisinde belirttiğim yere gene ip değerimizi yazmalıyız.
if __name__ == '__main__':
    app.run(host='xxx.xxx.x.xx', port=5000)