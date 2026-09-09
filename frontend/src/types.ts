export type Product = {
  id: string
  title: string
  subtitle: string
  description: string
  image: string
  price_ore: number
  /** Set only on discounted products; `price_ore` is then the reduced price. */
  original_price_ore: number | null
}

export type Cart = Record<string, number>

export type OrderLine = {
  product_id: string
  quantity: number
}
