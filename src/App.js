import "./App.css";

import Homepage from './Components/Homepage/Homepage';
import Home from './Components/Home/Home';

import { BrowserRouter, Route, Routes } from 'react-router-dom';


function App() {
  return (
    <BrowserRouter>
        <div className='app'>
          <Routes>
            <Route path='/mainpage' element={<Homepage />}></Route>
            <Route path='/home' element={<Home />}></Route>
          </Routes>
        </div>
    </BrowserRouter>
  );
}

export default App;
