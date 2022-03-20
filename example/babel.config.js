module.exports = {
  presets: ['module:metro-react-native-babel-preset'],
  plugins: [
    'dynamic-import-node',
    [
      'module-resolver',
      {
        root: ['./src'],
        extensions: ['.ios.js', '.android.js', '.js', '.ts', '.tsx', '.json'],
        alias: {
          'components': './src/components',
          'screens': './src/screens',
        },
      },
    ]
  ],
};
