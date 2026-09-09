import { useEffect, useState } from 'react'
import { fetchProducts } from './api'
import Cart from './components/Cart'
import ProductList from './components/ProductList'
import type { Cart as CartState, Product } from './types'

export default function App() {

  return (
    <main className="layout">
      <ProductList />
      <Cart />
    </main>
  )
}
