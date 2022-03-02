import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from "./containers/Home";
import { Routes, Route } from "react-router-dom";
import Patients from "./containers/Patients";
import Profile from "./containers/Profile";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/patients" element={<Patients />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </div>
  );
}

export default App;
