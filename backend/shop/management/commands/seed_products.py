import json
from pathlib import Path

from django.core.management.base import BaseCommand

from shop.models import Product

PRODUCTS_JSON = Path(__file__).resolve().parents[3] / "products.json"


class Command(BaseCommand):
    help = "Load products.json into the database without creating duplicates."

    def handle(self, *args, **options):
        products = json.loads(PRODUCTS_JSON.read_text(encoding="utf-8"))["products"]

        for product in products:
            Product.objects.update_or_create(
                id=product["id"],
                defaults={
                    "title": product["title"],
                    "subtitle": product.get("subtitle", ""),
                    "description": product.get("description", ""),
                    "image": product["image"],
                    "price_ore": product["price"],
                    "original_price_ore": product.get("original_price_ore"),
                },
            )

        self.stdout.write(f"Seeded {len(products)} products.")
