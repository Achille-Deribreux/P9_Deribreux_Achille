import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from "./containers/Home";
import { Routes, Route, Link } from "react-router-dom";
import Patients from "./containers/Patients";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
          <Route path="/patients" element={<Patients />} />
      </Routes>
    </div>
  );
}

export default App;
