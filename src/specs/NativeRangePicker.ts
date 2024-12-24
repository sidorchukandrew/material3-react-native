import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface ShowProps {
  inputMode?: string;
  fullscreen?: boolean;
  start?: number;
  end?: number;
  positiveButtonText?: string;
  negativeButtonText?: string;
  title?: string;
  maxDate?: number;
  minDate?: number;
  firstDayOfWeek?: number;
}

export interface Spec extends TurboModule {
  show: (
    props: ShowProps,
    onChange: (startTimestamp: number, endTimestamp: number) => void,
    onCancel?: () => void
  ) => Promise<void>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RTNRangePicker');
