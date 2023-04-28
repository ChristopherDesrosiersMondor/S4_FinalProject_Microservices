import { Component, Fragment } from 'react';
import './App.css';

class App extends Component {

  render() {

    return(

      <div>

      <Fragment>
        <BrowserRouter>
            <Menu className='menu'>
              <MenuItem className='menuItem' as={NavLink} to>Home</MenuItem>
              <MenuItem className='menuItem'>Popular</MenuItem>
            </Menu>
        </BrowserRouter>
      </Fragment>

      </div>

    );

  }
}

export default App;
