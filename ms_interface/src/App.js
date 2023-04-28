import { Component, Fragment } from 'react';
import { BrowserRouter, NavLink, Route, Routes } from 'react-router-dom';
import { Menu, MenuItem } from 'semantic-ui-react';
import './App.css';
import Page404 from './Composants/Page404';
import Home from './Composants/Home';
import Popular from './Composants/Popular';

class App extends Component {

  render() {

    return(

      <div>

      <Fragment>
        <BrowserRouter>
            <Menu className='menu'>
              <MenuItem className='menuItem' as={NavLink} to='/' exact='true'>Home</MenuItem>
              <MenuItem className='menuItem' as={NavLink} to='/popular'>Popular</MenuItem>
            </Menu>

          <Routes>
            <Route exact path='/' element={<Home/>}/>
            <Route exact path='/popular' element={<Popular/>}/>
            <Route path='*' element={<Page404/>}/>
          </Routes>

        </BrowserRouter>
      </Fragment>

      </div>

    );

  }
}

export default App;
