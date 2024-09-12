import { useState } from 'react';
import './App.css';
import siteIcon from './images_Used/pokeBall.png'

function App() {
  const [navList, setNavList] = useState(["nav-list"])
  const [activeInNavList, setActiveInNavList] = useState(true)
  var showList = () => {
    if (activeInNavList) {
      setActiveInNavList(!activeInNavList)
      setNavList(['nav-list active'])
    } else {
      setActiveInNavList(!activeInNavList)
      setNavList(['nav-list'])
    }
  }
  return (
    <div>
      <nav className="navbar">
        <div style={{ padding: '4px' }}>
          <a href="index.html"><img className="primary-icon" src={siteIcon} alt="Logo " /></a>
        </div>
        <div className="hamParent" id="hamParent" onClick={showList}>
          <div className="hamLine"></div>
          <div className="hamLine"></div>
          <div className="hamLine"></div>
        </div>
        <ul className={[navList].join(" ")} id="nav-list">
          <li className="list-item"><a href="index.html">Home</a></li>
          <li className="list-item"><a href="index.html">Pokemon</a></li>
          <li className="list-item"><a href="index.html">About</a></li>
          <li className="list-item"><a href="index.html">Support</a></li>
          <li className="list-item"><a href="index.html">Contact</a></li>
          <li className="list-item"><a href="index.html">Join</a></li>
        </ul>
      </nav >

      <div className="mainBody">
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dolores quibusdam architecto unde magnam veniam illo
        ad ipsa voluptatibus? Sed quisquam, commodi autem cupiditate aperiam exercitationem, nam aspernatur temporibus
        explicabo debitis minima magni reiciendis.
        <br />
        <h3>
          From here we will add the body to the app.
        </h3>
      </div>

    </div >
  );
}

export default App;
