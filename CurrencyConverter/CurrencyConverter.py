from flask import Flask, jsonify, request

app = Flask(__name__)

# Sample exchange rates (base currency: USD)
exchange_rates = {
    "USD": 1.0,
    "EUR": 0.92,
    "GBP": 0.81,
    "INR": 82.65,
    "JPY": 114.02,
    "CAD": 1.25,
}

@app.route('/convert', methods=['GET'])
def convert_currency():
    """
    Convert from one currency to another.
    Query parameters:
        - amount (float): The amount to convert.
        - from_currency (str): The source currency.
        - to_currency (str): The target currency.
    """
    try:
        amount = float(request.args.get('amount', 0))
        from_currency = request.args.get('from_currency', 'USD').upper()
        to_currency = request.args.get('to_currency', 'USD').upper()

        if from_currency not in exchange_rates or to_currency not in exchange_rates:
            return jsonify({"error": "Invalid currency code"}), 400

        # Conversion logic
        converted_amount = amount / exchange_rates[from_currency] * exchange_rates[to_currency]
        return jsonify({
            "amount": amount,
            "from_currency": from_currency,
            "to_currency": to_currency,
            "converted_amount": round(converted_amount, 2)
        })

    except ValueError:
        return jsonify({"error": "Invalid amount"}), 400
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
