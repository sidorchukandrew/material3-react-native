import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface ShowProps {
  inputMode?: string;
  positiveButtonText?: string;
  negativeButtonText?: string;
  title?: string;
  is24HourFormat?: boolean;
  hour?: number;
  minute?: number;
}

export interface Spec extends TurboModule {
  show: (
    props: ShowProps,
    onChange: (result: { hour: number; minute: number }) => void,
    onCancel?: () => void
  ) => Promise<void>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RTNTimePicker');
