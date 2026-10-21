# Mini Oda Shop — skeleton

Django + SQLite backend, with two clients to choose from: a React + TypeScript (Vite)
web frontend and a Kotlin + Jetpack Compose Android app. Build the one your assignment
names. Browse products, add them to a cart, place an order.

This is a starting point, not a working shop. The parts the assignment asks you to
build are stubbed out and marked `TODO`. Everything around them — project config,
dev server, API proxy, the `Product` model and its endpoint, type definitions — is
already wired up, so you can spend your two hours on the assignment instead of setup.

## Prerequisites

Python 3, any recent version. Check with `python3 --version`. The web frontend also
needs Node (`node --version`). The Android app needs a recent stable Android Studio
with an emulator set up.

## Run

### Backend

On port 8000:

```bash
python3 -m venv .venv
.venv/bin/pip install -r backend/requirements.txt
.venv/bin/python backend/manage.py migrate
.venv/bin/python backend/manage.py seed_products
.venv/bin/python backend/manage.py runserver
```

### Web frontend

On port 5173, in a second terminal:

```bash
cd frontend
npm install
npm run dev
```

Open http://localhost:5173. You should see the product count and an empty cart. The
Vite dev server proxies `/api` to Django, so the browser only ever talks to one
origin and there is no CORS setup to worry about.

### Android app

Open the `android/` folder in Android Studio, let Gradle sync, and run the `app`
configuration on an emulator. You should see the products in a grid. Keep
`runserver` going: the app reaches it at `http://10.0.2.2:8000`, which is the
emulator's address for your computer.

On a physical device instead, run `adb reverse tcp:8000 tcp:8000` and change
`API_BASE_URL` in `android/app/build.gradle.kts` to `http://localhost:8000/api/`.

Unit tests run with `./gradlew test` from `android/`, or from Android Studio.

### Seeding

Run `seed_products` again whenever you change `products.json`. It updates the
existing rows instead of duplicating them, so it is safe to repeat.

## Layout

```
backend/
  config/          settings, root urls, wsgi
  shop/
    models.py      database models — Product is done, the order is yours
    views.py       request handlers: they take an HTTP request and return a response
    urls.py        maps a URL path to a view
    management/commands/seed_products.py   loads products.json into SQLite
  products.json    22 products, already here
frontend/src/
  App.tsx          fetches products, owns the cart state
  api.ts           fetch helpers for both endpoints
  types.ts         Product, Cart, OrderLine
  tokens.css       a small slice of Oda's design tokens
  index.css        the two-column shell and the cart panel
  components/      ProductList.tsx and Cart.tsx
android/app/src/main/java/no/oda/shop/
  MainActivity.kt  sets the theme and shows the product list
  data/
    Models.kt      Product, and the order request and response
    ShopApi.kt     Retrofit calls for both endpoints
  ui/
    Format.kt      formatOre, so 3990 shows as 39,90 kr
    theme/         the same slice of Oda's design tokens, as Compose colors
    products/      the product grid, and its ViewModel holding the screen's state
android/app/src/test/   unit tests, with a rule that runs viewModelScope in a test
```

NB! Money is stored as whole øre, so 3090 means 30,90 kr. Integers avoid the
rounding errors that floats bring, so keep prices in øre everywhere and format them
only when you show them.

NB! Django's CSRF check rejects every POST that arrives without a CSRF token, and
neither app sends one. Put `@csrf_exempt` (from `django.views.decorators.csrf`) on
the order view, or it answers 403.

Add files and folders wherever it helps. Nothing here is fixed.

## Design

`designs/cart-empty.png` and `designs/cart-filled.png` show the shop with an empty
and an active cart. Use them for inspiration. 

## Before you submit

Replace this README with your own: how to run the app, what you cut or simplified to
fit the two hours, what you would do next with more time, and where AI tools helped
or got in the way.
