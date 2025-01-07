// // import { useState } from 'react'
// // import reactLogo from './assets/react.svg'
// // import viteLogo from '/vite.svg'
// import './App.css'
// import {DefaultLayout} from "./components/layouts/default";
// import AppRoutes from "./routes/AppRoutes.tsx";
//
// const App = () =>
//         <AppRoutes />
//
// //function App() {
// //   const [count, setCount] = useState(0)
// //
// //   return (
// //     <>
// //       <div>
// //         <a href="https://vite.dev" target="_blank">
// //           <img src={viteLogo} className="logo" alt="Vite logo" />
// //         </a>
// //         <a href="https://react.dev" target="_blank">
// //           <img src={reactLogo} className="logo react" alt="React logo" />
// //         </a>
// //       </div>
// //       <h1>Vite + React</h1>
// //       <div className="card">
// //         <button onClick={() => setCount((count) => count + 1)}>
// //           count is {count}
// //         </button>
// //         <p>
// //           Edit <code>src/App.tsx</code> and save to test HMR
// //         </p>
// //       </div>
// //       <p className="read-the-docs">
// //         Click on the Vite and React logos to learn more
// //       </p>
// //     </>
// //   )
// // }
//
// export default App;


import './App.css'

import {BrowserRouter as Router} from 'react-router-dom'
import {RoutesComponent} from "./router";
import {FadingAlertContextProvider} from "./contexts/FadingAlert/FadingAlertContext.tsx";
import {UserProvider} from "./model/UserContext.tsx";

export const App = () => (
    // Dostarcza komponentom aplikacji kontekst, który umożliwia pokazywanie okna alertu
    <UserProvider>
        {/*Test czy to działa*/}
        <FadingAlertContextProvider>
            {/* Włącza mechanizm rutera - nawigacji po ścieżkach aplikacji */}
            <Router>
                <RoutesComponent/>
            </Router>
        </FadingAlertContextProvider>
    </UserProvider>
)

export default App
