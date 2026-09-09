import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

export default defineConfig({
  plugins: [react()],
  // Keeps the browser on one origin, so no CORS setup is needed.
  server: { proxy: { '/api': 'http://localhost:8000' } },
})
