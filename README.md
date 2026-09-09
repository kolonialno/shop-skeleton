# Mini Oda Shop — skeleton

Django + SQLite backend, React + TypeScript (Vite) frontend. Browse products, add
them to a cart, place an order.

This is a starting point, not a working shop. The parts the assignment asks you to
build are stubbed out and marked `TODO`. Everything around them — project config,
dev server, API proxy, migrations, type definitions — is already wired up, so you
can spend your time on the assignment instead of setup.

## Prerequisites

Python 3 and Node, any recent version. Check with `python3 --version` and
`node --version`.

## Run

Backend, on port 8000:

```bash
python3 -m venv .venv
.venv/bin/pip install -r backend/requirements.txt
.venv/bin/python backend/manage.py migrate
.venv/bin/python backend/manage.py seed_products
.venv/bin/python backend/manage.py runserver
```

Frontend, on port 5173, in a second terminal:

```bash
cd frontend
npm install
npm run dev
```

Open http://localhost:5173. The Vite dev server proxies `/api` to Django, so the
browser only ever talks to one origin and there's no CORS setup to worry about.

Run `seed_products` again whenever you change `products.json`. It updates the
existing rows instead of duplicating them, so it's safe to repeat.

## Layout

```
backend/
  config/          settings, root urls, wsgi
  shop/
    models.py      database models — Product is done, Order and OrderItem are yours
    views.py       request handlers: they take an HTTP request and return a response
    urls.py        maps a URL path to a view
    management/commands/seed_products.py   loads products.json into SQLite
  products.json    22 products, already here
frontend/src/
  App.tsx          owns products + cart state
  api.ts           fetch helpers for both endpoints
  types.ts         Product, Cart, OrderLine
  tokens.css       a small slice of Oda's design tokens
  components/      ProductList.tsx and Cart.tsx
```

NB! Money is stored as whole øre, so 3090 means 30,90 kr. Integers avoid rounding
errors that floats bring, so keep prices in øre everywhere and format them only
when you show them.

Add files and folders wherever it helps. Nothing here is fixed. 

## Design

Use the design in `designs/shop.png`  for inspiration.

## Before you submit

Replace this README with your own: how to run the app, the technical choices and
tradeoffs you made, and optionally how AI tools helped or got in the way.
