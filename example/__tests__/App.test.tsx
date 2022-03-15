import 'react-native';
import React from 'react';
import { render } from '@testing-library/react-native';
import { App } from '../src/App';

jest.mock('@notifee/react-native', () => ({
  requestPermission: jest.fn(),
  onForegroundEvent: jest.fn(),
}));

it('renders correctly', () => {
  const { toJSON } = render(<App />);
  expect(toJSON()).toMatchSnapshot();
});
